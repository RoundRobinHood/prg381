package com.belgiumcampus.wellness.view;

import com.belgiumcampus.wellness.util.MenuItem;
import com.belgiumcampus.wellness.util.UserRole;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class MainDashboardPanel {

    private JTabbedPane HomeTabbedPanel;
    private JPanel StudentManagementTab;
    private JPanel MainPanel;
    private JPanel AppointmentManagementTab;
    private JPanel CounselorManagementTab;
    private JPanel FeedbackManagementTab;
    private JPanel BookAppointmentTab;
    private JPanel MyAppointmentsTab;
    private JPanel ViewCounselorsTab; // Corrected field name (was ViewCouncelorsTab)
    private JPanel SubmitFeedbackTab;
    private JPanel MyFeedbackHistoryTab;
    private JPanel HomeTab;
    private JButton AddStudent;
    private JButton Delete;
    private JButton AddAppointmentButton;
    private JButton DeleteButton;
    private JButton Update;
    private JTextField StudentIDTextBox;
    private JTextField AppointmentIDTextBox;
    private JButton UpdateButton;
    private JTextField CounselorIDTextBox;
    private JTextField AppointmentDateTextBox;
    private JTextField AppointmentTimeTextBox;
    private JTextField NameTextBox;
    private JTextField EmailTextBox;
    private JTextField SurnameTextBox;
    private JTextField PhoneNumberTextBOx;
    private JPasswordField passwordField1;
    private JTextField StudentNumberTextBox;
    private JLabel PasswordTextBox;
    private JTable AppointmentTable;
    private JTable StudentsTable;

    // Components for BookAppointmentTab
    private JLabel HeadingLabel;
    private JLabel CounselorLabel;
    private JComboBox CounselorComboBox;
    private JLabel DateLabel;
    private JFormattedTextField DateTextField;
    private JLabel TimeLabel;
    private JComboBox TimeComboBox;
    private JButton BookAppointmentButton;

    // Components for MyAppointmentsTab
    private JTable MyAppointmentsTable;
    private DefaultTableModel myAppointmentsTableModel;
    private JPanel HeaderPanel;
    private JButton RefreshButton;
    private JTextField FilterTextBox;
    private JComboBox FilterComboBox;
    private JLabel FilterLabel;
    private JPanel FooterPanel;
    private JButton PrintListButton;

    // Components for ViewCounselorsTab (corrected field name for the table)
    private JPanel ViewCouncelorsHeaderPanel; // This panel name seems fine as is if it matches form
    private JLabel SearchLabel;
    private JTextField SearchTextBox;
    private JLabel FilterByLabel;
    private JComboBox FilterByComboBox;
    private JTable ViewCounselorsTable; // <-- Corrected: Was ViewCouncelorsTable
    private JLabel SubmitFeedbackHeader;
    private JLabel AppointmentLabel;
    private JComboBox AppointmentComboBox;
    private JLabel RatingLabel;
    private JRadioButton RatingRadioButton1;
    private JRadioButton RatingRadioButton2;
    private JRadioButton RatingRadioButton3;
    private JRadioButton RatingRadioButton4;
    private JRadioButton RatingRadioButton5;
    private JLabel CommentsLabel;
    private JTextArea CommentsTextAreas;
    private JButton SubmitFeedbackButton;
    private DefaultTableModel viewCounselorsTableModel; // <-- NEW: Declare the table model for counselors table

    private UserRole currentUserRole;

    public MainDashboardPanel(UserRole role) {
        this.currentUserRole = role;

        // --- Initialize JTable's Model and Data for MyAppointmentsTable ---
        if (MyAppointmentsTable != null) {
            String[] columnNames = {"Counselor", "Date", "Time", "Status"};
            myAppointmentsTableModel = new DefaultTableModel(columnNames, 0);
            MyAppointmentsTable.setModel(myAppointmentsTableModel);
            loadAppointmentsData();
        }

        // --- NEW: Initialize JTable's Model and Data for ViewCounselorsTable ---
        if (ViewCounselorsTable != null) { // <-- Ensure it's not null and uses the corrected name
            String[] counselorColumnNames = {"Name", "Specialization", "Availability", "Rating"}; // Updated headers
            viewCounselorsTableModel = new DefaultTableModel(counselorColumnNames, 0);
            ViewCounselorsTable.setModel(viewCounselorsTableModel);
            loadCounselorsData(); // Call the method to load dummy data
        }
        // --- END NEW: ViewCounselorsTable Initialization ---


        // --- Initialize JComboBoxes with dummy data for now ---
        if (CounselorComboBox != null) {
            CounselorComboBox.addItem("Select Counselor");
            CounselorComboBox.addItem("Dr. Alex Smith");
            CounselorComboBox.addItem("Ms. Sarah Jones");
            CounselorComboBox.addItem("Mr. Chris Lee");
        }
        if (TimeComboBox != null) {
            TimeComboBox.addItem("Select Time");
            TimeComboBox.addItem("09:00 AM");
            TimeComboBox.addItem("10:00 AM");
            TimeComboBox.addItem("11:00 AM");
            TimeComboBox.addItem("02:00 PM");
        }
        // --- NEW: Initialize FilterByComboBox for View Counselors Tab ---
        if (FilterByComboBox != null) {
            FilterByComboBox.addItem("All Specializations");
            FilterByComboBox.addItem("Depression");
            FilterByComboBox.addItem("Anxiety");
            FilterByComboBox.addItem("Career");
            FilterByComboBox.addItem("Relationships");
        }
        // --- End JComboBoxes Initialization ---

        configureUIForRole();

        // --- Debugging Aid: Print visible tabs after filtering ---
        System.out.println("--- Dashboard Initialization Complete ---");
        System.out.println("Current Role: " + currentUserRole);
        if (HomeTabbedPanel != null) {
            System.out.println("Number of tabs in HomeTabbedPanel after filtering: " + HomeTabbedPanel.getTabCount());
            for (int i = 0; i < HomeTabbedPanel.getTabCount(); i++) {
                Component tabComponent = HomeTabbedPanel.getComponentAt(i);
                System.out.println("  Tab " + i + ": " + HomeTabbedPanel.getTitleAt(i) +
                        " (Component: " + tabComponent.getClass().getSimpleName() +
                        ", Field Name: " + getComponentName(tabComponent) + ")");
            }
        } else {
            System.out.println("HomeTabbedPanel is null or not initialized.");
        }
        System.out.println("---------------------------------------");
    }

    public JPanel getRootPanel() {
        return MainPanel;
    }

    private String getComponentName(Component comp) {
        if (comp == StudentManagementTab) return "StudentManagementTab";
        if (comp == AppointmentManagementTab) return "AppointmentManagementTab";
        if (comp == CounselorManagementTab) return "CounselorManagementTab";
        if (comp == FeedbackManagementTab) return "FeedbackManagementTab";
        if (comp == BookAppointmentTab) return "BookAppointmentTab";
        if (comp == MyAppointmentsTab) return "MyAppointmentsTab";
        if (comp == ViewCounselorsTab) return "ViewCounselorsTab"; // Corrected here too
        if (comp == SubmitFeedbackTab) return "SubmitFeedbackTab";
        if (comp == MyFeedbackHistoryTab) return "MyFeedbackHistoryTab";
        if (comp == HomeTab) return "HomeTab";
        return "Unknown/Generic Panel";
    }

    private void configureUIForRole() {
        if (HomeTabbedPanel == null) {
            System.err.println("Error: HomeTabbedPanel is null in configureUIForRole. Cannot filter tabs.");
            return;
        }

        List<Component> panelsToRemove = new ArrayList<>();

        if (currentUserRole == UserRole.STUDENT) {
            if (StudentManagementTab != null) panelsToRemove.add(StudentManagementTab);
            if (AppointmentManagementTab != null) panelsToRemove.add(AppointmentManagementTab);
            if (CounselorManagementTab != null) panelsToRemove.add(CounselorManagementTab);
            if (FeedbackManagementTab != null) panelsToRemove.add(FeedbackManagementTab);
        } else if (currentUserRole == UserRole.ADMIN) {
            if (BookAppointmentTab != null) panelsToRemove.add(BookAppointmentTab);
            if (MyAppointmentsTab != null) panelsToRemove.add(MyAppointmentsTab);
            if (ViewCounselorsTab != null) panelsToRemove.add(ViewCounselorsTab); // Corrected here too
            if (SubmitFeedbackTab != null) panelsToRemove.add(SubmitFeedbackTab);
            if (MyFeedbackHistoryTab != null) panelsToRemove.add(MyFeedbackHistoryTab);
        }

        for (int i = HomeTabbedPanel.getTabCount() - 1; i >= 0; i--) {
            Component tabComponent = HomeTabbedPanel.getComponentAt(i);
            if (panelsToRemove.contains(tabComponent)) {
                HomeTabbedPanel.removeTabAt(i);
            }
        }
    }

    private void loadAppointmentsData() {
        if (myAppointmentsTableModel != null) {
            myAppointmentsTableModel.setRowCount(0);
            myAppointmentsTableModel.addRow(new Object[]{"Dr. Alex Smith", "2025-07-20", "10:00 AM", "Confirmed"});
            myAppointmentsTableModel.addRow(new Object[]{"Ms. Sarah Jones", "2025-07-22", "02:30 PM", "Pending"});
            myAppointmentsTableModel.addRow(new Object[]{"Mr. Chris Lee", "2025-07-23", "09:15 AM", "Cancelled"});
        }
    }

    // --- NEW: Method to load dummy data for the counselors table ---
    private void loadCounselorsData() {
        // Corrected JTable field name here
        if (viewCounselorsTableModel != null) {
            viewCounselorsTableModel.setRowCount(0);

            // Add sample data with the new columns: Name, Specialization, Availability, Rating
            viewCounselorsTableModel.addRow(new Object[]{"Dr. Emily White", "Depression, Anxiety", "Mon, Wed, Fri (General)", "4.8"});
            viewCounselorsTableModel.addRow(new Object[]{"Prof. Ben Carter", "Career, Stress Management", "Tue, Thu (General)", "4.5"});
            viewCounselorsTableModel.addRow(new Object[]{"Ms. Jessica Day", "Relationships, Grief", "Mon, Tue, Wed (General)", "4.9"});
            viewCounselorsTableModel.addRow(new Object[]{"Dr. John Doe", "Academic, Performance", "Weekdays (General)", "4.2"});
        }
    }
}