package com.belgiumcampus.wellness.util;

public enum MenuItem {
    APPOINTMENT_MANAGEMENT("Appointment Management"),
    COUNSELOR_MANAGEMENT("Counselor Management"),
    FEEDBACK_MANAGEMENT("Feedback Management"),
    LOGOUT("Logout"); // Optional, if you want a logout option in desktop app

    private final String displayName;

    MenuItem(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}