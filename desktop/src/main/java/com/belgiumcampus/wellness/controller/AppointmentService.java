package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Appointment;
import com.belgiumcampus.wellness.classes.Appointment.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentService {

    /**
     * Adds a new appointment via the API (placeholder for now).
     * @param studentNumber The student's ID (FK).
     * @param counselorId The counsellor's ID (FK).
     * @param date The appointment date.
     * @param time The appointment time.
     * @return The created Appointment object.
     */
    public Appointment addAppointment(String appointmentID, String studentNumber, String counselorId, String date, String time) {
        System.out.println("AppointmentService: Attempting to add appointment (API call placeholder)");
        // In the future, this will make an HTTP POST request to your API
        Appointment newAppointment = Appointment.addAppointment(appointmentID,studentNumber, counselorId, date, time);
        System.out.println("AppointmentService: Appointment added locally - " + newAppointment.getAppointmentId());
        return newAppointment;
    }

    /**
     * Retrieves an appointment by its ID via the API (placeholder for now).
     * @param appointmentId The unique appointment ID.
     * @return The Appointment object, or null if not found.
     */
    public Appointment getAppointmentById(String appointmentId) {
        System.out.println("AppointmentService: Attempting to retrieve appointment by ID (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        for (Appointment appointment : Appointment.AllAppointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        System.out.println("AppointmentService: Appointment with ID " + appointmentId + " not found locally.");
        return null;
    }

    /**
     * Retrieves all appointments via the API (placeholder for now).
     * @return A list of all Appointment objects.
     */
    public List<Appointment> getAllAppointments() {
        System.out.println("AppointmentService: Attempting to retrieve all appointments (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        return Appointment.AllAppointments;
    }

    /**
     * Updates an existing appointment's date, time, and status via the API (placeholder for now).
     * @param appointmentId The ID of the appointment to update.
     * @param newDate New date.
     * @param newTime New time.
     * @param newStatus New status.
     * @return true if the appointment was found and updated, false otherwise.
     */
    public boolean updateAppointment(String appointmentId, String newDate, String newTime) {
        System.out.println("AppointmentService: Attempting to update appointment (API call placeholder)");
        // In the future, this will make an HTTP PUT request to your API
        return Appointment.updateAppointment(appointmentId, newDate, newTime);
    }

    /**
     * Deletes an appointment via the API (placeholder for now).
     * @param appointmentId The ID of the appointment to delete.
     * @return true if the appointment was found and deleted, false otherwise.
     */
    public boolean deleteAppointment(String appointmentId) {
        System.out.println("AppointmentService: Attempting to delete appointment (API call placeholder)");
        // In the future, this will make an HTTP DELETE request to your API
        return Appointment.deleteAppointment(appointmentId);
    }
}