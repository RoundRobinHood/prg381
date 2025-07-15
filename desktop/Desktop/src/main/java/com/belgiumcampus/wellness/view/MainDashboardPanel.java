package com.belgiumcampus.wellness.view;

import com.belgiumcampus.wellness.util.MenuItem;
import javax.swing.*;
import java.awt.*;

public class MainDashboardPanel extends JPanel {

    private JTabbedPane tabbedPane;

    public MainDashboardPanel() {
        setLayout(new BorderLayout());

        initializeComponents();
        addComponents();
    }

    private void initializeComponents() {
        tabbedPane = new JTabbedPane();

        // Create placeholder panels for each management section
        // We'll replace these with actual content from AppointmentPanel, CounselorPanel, FeedbackPanel later
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.add(new JLabel(MenuItem.APPOINTMENT_MANAGEMENT.getDisplayName() + " - Content will go here"));
        appointmentPanel.setBackground(Color.LIGHT_GRAY);

        JPanel counselorPanel = new JPanel();
        counselorPanel.add(new JLabel(MenuItem.COUNSELOR_MANAGEMENT.getDisplayName() + " - Content will go here"));
        counselorPanel.setBackground(Color.CYAN);

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.add(new JLabel(MenuItem.FEEDBACK_MANAGEMENT.getDisplayName() + " - Content will go here"));
        feedbackPanel.setBackground(Color.YELLOW);

        // Add the placeholder panels to the tabbed pane using our MenuItem enum
        tabbedPane.addTab(MenuItem.APPOINTMENT_MANAGEMENT.getDisplayName(), appointmentPanel);
        tabbedPane.addTab(MenuItem.COUNSELOR_MANAGEMENT.getDisplayName(), counselorPanel);
        tabbedPane.addTab(MenuItem.FEEDBACK_MANAGEMENT.getDisplayName(), feedbackPanel);

        // Optional: Add a logout button or menu item, perhaps in a separate area if not using tabs for logout
        // For a simple tabbed interface, logout might be handled by a separate button on the main frame or via a menu bar.
        // For now, we'll focus on the core management tabs.
    }

    private void addComponents() {
        add(tabbedPane, BorderLayout.CENTER);
    }
}