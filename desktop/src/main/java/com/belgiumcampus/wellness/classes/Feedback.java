package com.belgiumcampus.wellness.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private String feedbackId; // PK
    private String appointmentId; // FK
    private String studentNumber; // FK
    private String rating; // e.g., 1-5
    private String comments;

    public static final List<Feedback> AllFeedback = new ArrayList<>();
    private static int nextId = 1;

    public Feedback(String appointmentId, String studentNumber, String rating, String comments) {
        this.feedbackId = "F" + String.format("%04d", nextId++);
        this.appointmentId = appointmentId;
        this.studentNumber = studentNumber;
        this.rating = rating;
        this.comments = comments;
    }

    // Constructor for loading from DB
    public Feedback(String feedbackId, String appointmentId, String studentNumber, String rating, String comments) {
        this.feedbackId = feedbackId;
        this.appointmentId = appointmentId;
        this.studentNumber = studentNumber;
        this.rating = rating;
        this.comments = comments;
    }

    // --- CRUD Operations (Local List) ---

    /**
     * Adds new feedback to the AllFeedback list.
     * @param appointmentId The appointment ID (FK).
     * @param studentNumber The student ID (FK).
     * @param rating The rating (1-5).
     * @param comments The comments.
     * @return The newly created Feedback object.
     */
    public static Feedback addFeedback(String feedbackId, String appointmentId, String studentNumber, String rating, String comments) {
        Feedback newFeedback = new Feedback(feedbackId,appointmentId, studentNumber, rating, comments);
        AllFeedback.add(newFeedback);
        return newFeedback;
    }

    /**
     * Updates an existing feedback's rating and comments.
     * @param feedbackId The ID of the feedback to update.
     * @param newRating New rating.
     * @param newComments New comments.
     * @return true if the feedback was found and updated, false otherwise.
     */
    public static boolean updateFeedback(String feedbackId, String newRating, String newComments) {
        for (Feedback feedback : AllFeedback) {
            if (feedback.getFeedbackId().equals(feedbackId)) {
                feedback.setRating(newRating);
                feedback.setComments(newComments);
                return true;
            }
        }
        System.err.println("Error: Feedback with ID " + feedbackId + " not found for update.");
        return false;
    }

    /**
     * Deletes feedback from the AllFeedback list.
     * @param feedbackId The ID of the feedback to delete.
     * @return true if the feedback was found and deleted, false otherwise.
     */
    public static boolean deleteFeedback(String feedbackId) {
        Feedback feedbackToRemove = null;
        for (Feedback feedback : AllFeedback) {
            if (feedback.getFeedbackId().equals(feedbackId)) {
                feedbackToRemove = feedback;
                break;
            }
        }
        if (feedbackToRemove != null) {
            AllFeedback.remove(feedbackToRemove);
            return true;
        }
        System.err.println("Error: Feedback with ID " + feedbackId + " not found for deletion.");
        return false;
    }

    // Getters and Setters (as before)
    public String getFeedbackId() { return feedbackId; }
    public String getAppointmentId() { return appointmentId; }
    public String getStudentNumber() { return studentNumber; }
    public String getRating() { return rating; }
    public String getComments() { return comments; }

    public void setRating(String rating) {
        if (rating.equals("1") || rating.equals("2") || rating.equals("3") || rating.equals("4") || rating.equals("5")) {
            this.rating = rating;
        } else {
            System.err.println("Rating must be between 1 and 5. Rating not updated.");
        }
    }
    public void setComments(String comments) { this.comments = comments; }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId='" + feedbackId + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(feedbackId, feedback.feedbackId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId);
    }
}