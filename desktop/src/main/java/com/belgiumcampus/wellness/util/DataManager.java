package com.belgiumcampus.wellness.util;

import java.io.*;
import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class DataManager extends Thread {
    private static final String DB_PATH = "my_local_db";
    private static final String DB_URL = "jdbc:derby:" + DB_PATH + ";create=true";
    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    static {
        try {
            if (!new File(DB_PATH).exists()) {
                System.out.println("DB not found, running init.sql...");
                try (Connection conn = getConnection()) {
                    runInitScript(conn, "/init.sql");
                }
            } else {
                System.out.println("DB exists, skipping init.");
            }
            initialized.set(true);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize DB", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // Each caller gets a fresh Connection; caller responsible for closing
        return DriverManager.getConnection(DB_URL);
    }

    private static void runInitScript(Connection conn, String resourcePath) throws IOException, SQLException {
        InputStream is = DataManager.class.getResourceAsStream(resourcePath);
        if (is == null) throw new FileNotFoundException("Init script not found: " + resourcePath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--")) continue;

                sql.append(line);
                if (line.endsWith(";")) {
                    String statement = sql.substring(0, sql.length() - 1); // remove semicolon
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(statement);
                    }
                    sql.setLength(0);
                }
            }
        }
    }

    private volatile boolean running = true;

    public void shutdown() {
        running = false;
        this.interrupt();
    }

    @Override
    public void run() {
        if (!initialized.get()) {
            throw new IllegalStateException("DB not initialized");
        }

        while (running) {
            try {
                syncTables();
                Thread.sleep(5000); // wait 5 seconds between sync passes
            } catch (InterruptedException e) {
                if (!running) break;
            } catch (Exception e) {
                e.printStackTrace();
                // optionally add backoff or error counting here
            }
        }
    }

    private void syncTables() throws SQLException {
        // Order matters to respect foreign keys:
        // students, counselors, appointments, feedback
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            // Upload phase
            uploadModifiedRows(conn, "students");
            uploadDeletedRows(conn, "students");

            uploadModifiedRows(conn, "counselors");
            uploadDeletedRows(conn, "counselors");

            uploadModifiedRows(conn, "appointments");
            uploadDeletedRows(conn, "appointments");

            uploadModifiedRows(conn, "feedback");
            uploadDeletedRows(conn, "feedback");

            // Download phase
            downloadAndUpsert(conn, "students");
            downloadAndUpsert(conn, "counselors");
            downloadAndUpsert(conn, "appointments");
            downloadAndUpsert(conn, "feedback");

            conn.commit();
        }
    }

    private void uploadModifiedRows(Connection conn, String table) throws SQLException {
        // Select all rows with local_modified = true and local_deleted = false
        String sql = "SELECT * FROM " + table + " WHERE local_modified = TRUE AND local_deleted = FALSE";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // TODO: Call your API upload method for this row here
                // Example: uploadRowToServer(table, rs);

                // On success:
                markRowClean(conn, table, rs);
            }
        }
    }

    private void uploadDeletedRows(Connection conn, String table) throws SQLException {
        // Select all rows marked for deletion
        String sql = "SELECT * FROM " + table + " WHERE local_deleted = TRUE";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // TODO: Call your API delete method for this row here
                // Example: deleteRowOnServer(table, rs);

                // On success, delete locally (or keep tombstone if you want)
                deleteLocalRow(conn, table, rs);
            }
        }
    }

    private void downloadAndUpsert(Connection conn, String table) throws SQLException {
        // TODO: fetch all records from server for this table
        // For each record:
        //   - upsert into local DB, skipping rows where local_deleted=true
    }

    private void markRowClean(Connection conn, String table, ResultSet rs) throws SQLException {
        // Assuming your PK column is called "id" or customize per table
        String pk = getPrimaryKeyColumn(table);
        Object id = rs.getObject(pk);
        String sql = "UPDATE " + table + " SET local_modified = FALSE WHERE " + pk + " = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        }
    }

    private void deleteLocalRow(Connection conn, String table, ResultSet rs) throws SQLException {
        String pk = getPrimaryKeyColumn(table);
        Object id = rs.getObject(pk);
        String sql = "DELETE FROM " + table + " WHERE " + pk + " = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        }
    }

    private String getPrimaryKeyColumn(String table) {
        return switch (table) {
            case "students" -> "student_number";
            case "counselors" -> "counselor_id";
            case "appointments" -> "appointment_id";
            case "feedback" -> "feedback_id";
            default -> throw new IllegalArgumentException("Unknown table: " + table);
        };
    }
}

