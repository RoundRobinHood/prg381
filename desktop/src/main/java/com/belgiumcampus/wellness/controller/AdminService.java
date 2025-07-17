package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Admin;

import java.util.List;

public class AdminService {

    /**
     * Adds a new admin via the API (placeholder for now).
     * @param name Admin's name.
     * @param surname Admin's surname.
     * @param email Admin's email.
     * @param phone Admin's phone number.
     * @param password Admin's password.
     * @return The created Admin object, or null if creation failed.
     */
    public Admin addAdmin(String adminID, String name, String surname, String email, String phone, String password) {
        System.out.println("AdminService: Attempting to add admin (API call placeholder)");
        // In the future, this will make an HTTP POST request to your API
        Admin newAdmin = Admin.addAdmin(adminID, name, surname, email, phone, password);
        if (newAdmin != null) {
            System.out.println("AdminService: Admin added locally - " + newAdmin.getEmail());
        }
        return newAdmin;
    }

    /**
     * Retrieves an admin by their email via the API (placeholder for now).
     * @param email The unique email of the admin.
     * @return The Admin object, or null if not found.
     */
    public Admin getAdminByEmail(String email) {
        System.out.println("AdminService: Attempting to retrieve admin by email (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        for (Admin admin : Admin.AllAdmins) {
            if (admin.getEmail().equals(email)) {
                return admin;
            }
        }
        System.out.println("AdminService: Admin with email " + email + " not found locally.");
        return null;
    }

    /**
     * Retrieves all admins via the API (placeholder for now).
     * @return A list of all Admin objects.
     */
    public List<Admin> getAllAdmins() {
        System.out.println("AdminService: Attempting to retrieve all admins (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        return Admin.AllAdmins;
    }

    /**
     * Updates an existing admin's information via the API (placeholder for now).
     * @param email The email of the admin to update.
     * @param newName New name.
     * @param newSurname New surname.
     * @param newPhone New phone number.
     * @param newPassword New password.
     * @return true if the admin was found and updated, false otherwise.
     */
    public boolean updateAdmin(String adminID, String email, String newName, String newSurname, String newPhone, String newPassword) {
        System.out.println("AdminService: Attempting to update admin (API call placeholder)");
        // In the future, this will make an HTTP PUT request to your API
        return Admin.updateAdmin(adminID, email, newName, newSurname, newPhone, newPassword);
    }

    /**
     * Deletes an admin via the API (placeholder for now).
     * @param email The email of the admin to delete.
     * @return true if the admin was found and deleted, false otherwise.
     */
    public boolean deleteAdmin(String email) {
        System.out.println("AdminService: Attempting to delete admin (API call placeholder)");
        // In the future, this will make an HTTP DELETE request to your API
        return Admin.deleteAdmin(email);
    }
}
