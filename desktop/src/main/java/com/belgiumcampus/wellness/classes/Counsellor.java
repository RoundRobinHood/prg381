package com.belgiumcampus.wellness.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Counsellor {
    private String counselorId; // PK
    private String name;
    private String specialization;
    private String availability;

    public static final List<Counsellor> AllCounsellors = new ArrayList<>();
    private static int nextId = 1;

    public Counsellor(String name, String specialization, String availability) {
        this.counselorId = "C" + String.format("%03d", nextId++);
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    // Constructor for loading from DB (where ID is already known)
    public Counsellor(String counselorId, String name, String specialization, String availability) {
        this.counselorId = counselorId;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    // --- CRUD Operations (Local List) ---

    /**
     * Adds a new counsellor to the AllCounsellors list.
     * @param name Counsellor's name.
     * @param specialization Counsellor's specialization.
     * @param availability Counsellor's availability string.
     * @return The newly created Counsellor object.
     */
    public static Counsellor addCounsellor(String name, String specialization, String availability) {
        Counsellor newCounsellor = new Counsellor(name, specialization, availability);
        AllCounsellors.add(newCounsellor);
        return newCounsellor;
    }

    /**
     * Updates an existing counsellor's information.
     * @param counselorId The ID of the counsellor to update.
     * @param newName New name.
     * @param newSpecialization New specialization.
     * @param newAvailability New availability string.
     * @return true if the counsellor was found and updated, false otherwise.
     */
    public static boolean updateCounsellor(String counselorId, String newName, String newSpecialization, String newAvailability) {
        for (Counsellor counsellor : AllCounsellors) {
            if (counsellor.getCounselorId().equals(counselorId)) {
                counsellor.setName(newName);
                counsellor.setSpecialization(newSpecialization);
                counsellor.setAvailability(newAvailability);
                return true;
            }
        }
        System.err.println("Error: Counsellor with ID " + counselorId + " not found for update.");
        return false;
    }

    /**
     * Deletes a counsellor from the AllCounsellors list.
     * @param counselorId The ID of the counsellor to delete.
     * @return true if the counsellor was found and deleted, false otherwise.
     */
    public static boolean deleteCounsellor(String counselorId) {
        Counsellor counsellorToRemove = null;
        for (Counsellor counsellor : AllCounsellors) {
            if (counsellor.getCounselorId().equals(counselorId)) {
                counsellorToRemove = counsellor;
                break;
            }
        }
        if (counsellorToRemove != null) {
            AllCounsellors.remove(counsellorToRemove);
            return true;
        }
        System.err.println("Error: Counsellor with ID " + counselorId + " not found for deletion.");
        return false;
    }

    // Getters and Setters (as before)
    public String getCounselorId() { return counselorId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getAvailability() { return availability; }

    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setAvailability(String availability) { this.availability = availability; }

    @Override
    public String toString() {
        return "Counsellor{" +
                "counselorId='" + counselorId + '\'' +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counsellor that = (Counsellor) o;
        return Objects.equals(counselorId, that.counselorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counselorId);
    }
}