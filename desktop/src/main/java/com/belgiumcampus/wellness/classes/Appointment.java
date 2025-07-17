package com.belgiumcampus.wellness.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Appointment {

    public enum AppointmentStatus {
        SCHEDULED,
        COMPLETED,
        CANCELLED
    }

    private String appointmentId; // PK
    private String studentNumber; // FK
    private String counselorId;   // FK
    private LocalDate date;
    private LocalTime time;
    private AppointmentStatus status;

    public static final List<Appointment> AllAppointments = new ArrayList<>();
    private static int nextId = 1;

    public Appointment(String studentNumber, String counselorId, LocalDate date, LocalTime time) {
        this.appointmentId = "A" + String.format("%04d", nextId++);
        this.studentNumber = studentNumber;
        this.counselorId = counselorId;
        this.date = date;
        this.time = time;
        this.status = AppointmentStatus.SCHEDULED;
    }

    // Constructor for loading from DB
    public Appointment(String appointmentId, String studentNumber, String counselorId, LocalDate date, LocalTime time, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.studentNumber = studentNumber;
        this.counselorId = counselorId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    // --- CRUD Operations (Local List) ---

    /**
     * Adds a new appointment to the AllAppointments list.
     * @param studentNumber The student's ID (FK).
     * @param counselorId The counsellor's ID (FK).
     * @param date The appointment date.
     * @param time The appointment time.
     * @return The newly created Appointment object.
     */
    public static Appointment addAppointment(String studentNumber, String counselorId, LocalDate date, LocalTime time) {
        Appointment newAppointment = new Appointment(studentNumber, counselorId, date, time);
        AllAppointments.add(newAppointment);
        return newAppointment;
    }

    /**
     * Updates an existing appointment's date, time, and status.
     * @param appointmentId The ID of the appointment to update.
     * @param newDate New date.
     * @param newTime New time.
     * @param newStatus New status.
     * @return true if the appointment was found and updated, false otherwise.
     */
    public static boolean updateAppointment(String appointmentId, LocalDate newDate, LocalTime newTime, AppointmentStatus newStatus) {
        for (Appointment appointment : AllAppointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointment.setDate(newDate);
                appointment.setTime(newTime);
                appointment.setStatus(newStatus);
                return true;
            }
        }
        System.err.println("Error: Appointment with ID " + appointmentId + " not found for update.");
        return false;
    }

    /**
     * Deletes an appointment from the AllAppointments list.
     * @param appointmentId The ID of the appointment to delete.
     * @return true if the appointment was found and deleted, false otherwise.
     */
    public static boolean deleteAppointment(String appointmentId) {
        Appointment appointmentToRemove = null;
        for (Appointment appointment : AllAppointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointmentToRemove = appointment;
                break;
            }
        }
        if (appointmentToRemove != null) {
            AllAppointments.remove(appointmentToRemove);
            return true;
        }
        System.err.println("Error: Appointment with ID " + appointmentId + " not found for deletion.");
        return false;
    }

    // Getters and Setters (as before)
    public String getAppointmentId() { return appointmentId; }
    public String getStudentNumber() { return studentNumber; }
    public String getCounselorId() { return counselorId; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public AppointmentStatus getStatus() { return status; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", counselorId='" + counselorId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(appointmentId, that.appointmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId);
    }
}