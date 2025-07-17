package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Feedback;

import java.util.List;

public class FeedbackService {

    /**
     * Adds new feedback via the API (placeholder for now).
     * @param appointmentId The appointment ID (FK).
     * @param studentNumber The student ID (FK).
     * @param rating The rating (1-5).
     * @param comments The comments.
     * @return The created Feedback object.
     */
    public Feedback addFeedback(String appointmentId, String studentNumber, int rating, String comments) {
        System.out.println("FeedbackService: Attempting to add feedback (API call placeholder)");
        // In the future, this will make an HTTP POST request to your API
        Feedback newFeedback = Feedback.addFeedback(appointmentId, studentNumber, rating, comments);
        System.out.println("FeedbackService: Feedback added locally - " + newFeedback.getFeedbackId());
        return newFeedback;
    }

    /**
     * Retrieves feedback by its ID via the API (placeholder for now).
     * @param feedbackId The unique feedback ID.
     * @return The Feedback object, or null if not found.
     */
    public Feedback getFeedbackById(String feedbackId) {
        System.out.println("FeedbackService: Attempting to retrieve feedback by ID (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        for (Feedback feedback : Feedback.AllFeedback) {
            if (feedback.getFeedbackId().equals(feedbackId)) {
                return feedback;
            }
        }
        System.out.println("FeedbackService: Feedback with ID " + feedbackId + " not found locally.");
        return null;
    }

    /**
     * Retrieves all feedback entries via the API (placeholder for now).
     * @return A list of all Feedback objects.
     */
    public List<Feedback> getAllFeedback() {
        System.out.println("FeedbackService: Attempting to retrieve all feedback (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        return Feedback.AllFeedback;
    }

    /**
     * Updates an existing feedback's rating and comments via the API (placeholder for now).
     * @param feedbackId The ID of the feedback to update.
     * @param newRating New rating.
     * @param newComments New comments.
     * @return true if the feedback was found and updated, false otherwise.
     */
    public boolean updateFeedback(String feedbackId, int newRating, String newComments) {
        System.out.println("FeedbackService: Attempting to update feedback (API call placeholder)");
        // In the future, this will make an HTTP PUT request to your API
        return Feedback.updateFeedback(feedbackId, newRating, newComments);
    }

    /**
     * Deletes feedback via the API (placeholder for now).
     * @param feedbackId The ID of the feedback to delete.
     * @return true if the feedback was found and deleted, false otherwise.
     */
    public boolean deleteFeedback(String feedbackId) {
        System.out.println("FeedbackService: Attempting to delete feedback (API call placeholder)");
        // In the future, this will make an HTTP DELETE request to your API
        return Feedback.deleteFeedback(feedbackId);
    }
}