package com.belgiumcampus.wellness.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Admin extends User {
    private String AdminID;

    public static final List<Admin> AllAdmins = new ArrayList<>();

    public Admin(String adminId, String name, String surname, String email, String phone, String password) {
        super(name, surname, email, phone, password, "admin"); // Role is fixed as "admin"
        this.AdminID = adminId;
    }

    // --- CRUD Operations (Local List) ---

    /**
     * Adds a new admin to the AllAdmins list.
     * @param name Admin's name.
     * @param surname Admin's surname.
     * @param email Admin's email (unique for User).
     * @param phone Admin's phone number.
     * @param password Admin's password.
     * @return The newly created Admin object, or null if an admin with the same email already exists.
     */
    public static Admin addAdmin(String adminID, String name, String surname, String email, String phone, String password) {
        // Check for uniqueness based on email (as per User class equals/hashCode)
        for (Admin a : AllAdmins) {
            if (a.getEmail().equals(email)) {
                System.err.println("Error: Admin with email " + email + " already exists.");
                return null;
            }
        }
        Admin newAdmin = new Admin(adminID, name, surname, email, phone, password);
        AllAdmins.add(newAdmin);
        return newAdmin;
    }

    /**
     * Updates an existing admin's information.
     * @param adminID The email of the admin to update (PK equivalent).
     * @param newName New name.
     * @param newSurname New surname.
     * @param newPhone New phone number.
     * @param newPassword New password.
     * @return true if the admin was found and updated, false otherwise.
     */
    public static boolean updateAdmin(String adminID, String newName, String newSurname, String emailString, String newPhone, String newPassword) {
        for (Admin admin : AllAdmins) {
            if (admin.getAdminID().equals(adminID)) {
                admin.setName(newName);
                admin.setSurname(newSurname);
                admin.setEmail(emailString);
                admin.setPhone(newPhone);
                admin.setPassword(newPassword);
                return true;
            }
        }
        System.err.println("Error: Admin with id  " + adminID + " not found for update.");
        return false;
    }

    /**
     * Deletes an admin from the AllAdmins list.
     * @param adminID The email of the admin to delete.
     * @return true if the admin was found and deleted, false otherwise.
     */
    public static boolean deleteAdmin(String adminID) {
        Admin adminToRemove = null;
        for (Admin admin : AllAdmins) {
            if (admin.getAdminID().equals(adminID)) {
                adminToRemove = admin;
                break;
            }
        }
        if (adminToRemove != null) {
            AllAdmins.remove(adminToRemove);
            return true;
        }
        System.err.println("Error: Admin with ID " + adminID + " not found for deletion.");
        return false;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    // Getter
    public String getAdminID() {
        return AdminID;
    }
}
