package com.belgiumcampus.wellness;

import com.belgiumcampus.wellness.view.MainDashboardPanel;
import com.belgiumcampus.wellness.util.UserRole;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // --- Role Selection Pop-up (No change here) ---
            String[] options = {"Student", "Admin"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Select your user role:",
                    "User Role Selection",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            UserRole selectedRole;
            if (choice == JOptionPane.YES_OPTION) {
                selectedRole = UserRole.STUDENT;
                JOptionPane.showMessageDialog(null, "Logged in as Student.", "Role Confirmed", JOptionPane.INFORMATION_MESSAGE);
            } else if (choice == JOptionPane.NO_OPTION) {
                selectedRole = UserRole.ADMIN;
                JOptionPane.showMessageDialog(null, "Logged in as Admin.", "Role Confirmed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No role selected. Exiting application.", "Exiting", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                return;
            }
            // --- End Role Selection Pop-up ---

            // Create the main application frame
            JFrame frame = new JFrame("BC Student Wellness Management System - Desktop Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 550);
            frame.setMinimumSize(new Dimension(500, 350));
            frame.setLocationRelativeTo(null);

            // Create an instance of our MainDashboardPanel, passing the selected role
            MainDashboardPanel dashboardForm = new MainDashboardPanel(selectedRole); // Instantiate the bound class

            // Add the root panel of the form to the frame's content pane
            frame.getContentPane().add(dashboardForm.getRootPanel(), BorderLayout.CENTER); // Get the root panel from the form

            // Make the frame visible
            frame.setVisible(true);
        });
    }
}