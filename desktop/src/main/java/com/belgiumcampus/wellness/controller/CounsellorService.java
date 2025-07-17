package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Counsellor;

import java.util.List;

public class CounsellorService {

    /**
     * Adds a new counsellor via the API (placeholder for now).
     * @param name Counsellor's name.
     * @param specialization Counsellor's specialization.
     * @param availability Counsellor's availability string.
     * @return The created Counsellor object.
     */
    public Counsellor addCounsellor(String name, String specialization, String availability) {
        System.out.println("CounsellorService: Attempting to add counsellor (API call placeholder)");
        // In the future, this will make an HTTP POST request to your API
        Counsellor newCounsellor = Counsellor.addCounsellor(name, specialization, availability);
        System.out.println("CounsellorService: Counsellor added locally - " + newCounsellor.getCounselorId());
        return newCounsellor;
    }

    /**
     * Retrieves a counsellor by their ID via the API (placeholder for now).
     * @param counselorId The unique counsellor ID.
     * @return The Counsellor object, or null if not found.
     */
    public Counsellor getCounsellorById(String counselorId) {
        System.out.println("CounsellorService: Attempting to retrieve counsellor by ID (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        for (Counsellor counsellor : Counsellor.AllCounsellors) {
            if (counsellor.getCounselorId().equals(counselorId)) {
                return counsellor;
            }
        }
        System.out.println("CounsellorService: Counsellor with ID " + counselorId + " not found locally.");
        return null;
    }

    /**
     * Retrieves all counsellors via the API (placeholder for now).
     * @return A list of all Counsellor objects.
     */
    public List<Counsellor> getAllCounsellors() {
        System.out.println("CounsellorService: Attempting to retrieve all counsellors (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        return Counsellor.AllCounsellors;
    }

    /**
     * Updates an existing counsellor's information via the API (placeholder for now).
     * @param counsellorId The ID of the counsellor to update.
     * @param newName New name.
     * @param newSpecialization New specialization.
     * @param newAvailability New availability string.
     * @return true if the counsellor was found and updated, false otherwise.
     */
    public boolean updateCounsellor(String counsellorId, String newName, String newSpecialization, String newAvailability) {
        System.out.println("CounsellorService: Attempting to update counsellor (API call placeholder)");
        // In the future, this will make an HTTP PUT request to your API
        return Counsellor.updateCounsellor(counsellorId, newName, newSpecialization, newAvailability);
    }

    /**
     * Deletes a counsellor via the API (placeholder for now).
     * @param counselorId The ID of the counsellor to delete.
     * @return true if the counsellor was found and deleted, false otherwise.
     */
    public boolean deleteCounsellor(String counselorId) {
        System.out.println("CounsellorService: Attempting to delete counsellor (API call placeholder)");
        // In the future, this will make an HTTP DELETE request to your API
        return Counsellor.deleteCounsellor(counselorId);
    }
}