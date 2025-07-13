package com.belgiumcampus.wellness;

import com.belgiumcampus.wellness.view.MainDashboardPanel;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create the main application frame
            JFrame frame = new JFrame("BC Student Wellness Management System - Desktop Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setMinimumSize(new Dimension(800, 600));
            frame.setLocationRelativeTo(null);

            // Create an instance of our MainDashboardPanel
            MainDashboardPanel dashboardPanel = new MainDashboardPanel();

            // Add the dashboard panel to the frame's content pane
            frame.getContentPane().add(dashboardPanel, BorderLayout.CENTER);

            // Make the frame visible
            frame.setVisible(true);
        });
    }
}