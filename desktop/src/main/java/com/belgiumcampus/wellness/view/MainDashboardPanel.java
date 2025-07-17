package com.belgiumcampus.wellness.view;

import com.belgiumcampus.wellness.util.UserRole;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainDashboardPanel extends JPanel {

    private JTabbedPane HomeTabbedPanel;
    private JPanel StudentManagementTab;
    private JPanel MainPanel;
    private JPanel AppointmentManagementTab;
    private JPanel CounselorManagementTab;
    private JPanel FeedbackManagementTab;
    private JPanel BookAppointmentTab;
    private JPanel MyAppointmentsTab;
    private JPanel ViewCounselorsTab;
    private JPanel SubmitFeedbackTab;
    private JPanel MyFeedbackHistoryTab;
    private JPanel HomeTab;
    private JButton UserAddButton;
    private JButton UserDeleteButton;
    private JButton AppointmentAddButton;
    private JButton AppointmentDeleteButton;
    private JButton UserUpdateButton;
    private JTextField AppointmentStudentIDTextBox;
    private JTextField AppointmentIDTextBox;
    private JButton AppointmentUpdateButton;
    private JTextField AppointmentCounselorIDTextBox;
    private JTextField UserNameTextBox;
    private JTextField UserEmailTextBox;
    private JTextField UserSurnameTextBox;
    private JTextField UserPhoneNumberTextBOx;
    private JPasswordField UserPasswordField;
    private JTextField UserIDTextBox;
    private JLabel UserPasswordLabel;
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
    private JPanel ViewCouncelorsHeaderPanel;
    private JLabel SearchLabel;
    private JTextField SearchTextBox;
    private JLabel FilterByLabel;
    private JComboBox FilterByComboBox;
    private JTable ViewCounselorsTable;
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
    private JLabel UserEmailLabel;
    private JLabel UserSurnameLabel;
    private JLabel UserNameLabel;
    private JLabel UserPhoneNumberLabel;
    private JLabel UserIDLabel;
    private JLabel UserRoleLabel;
    private JComboBox UserRoleComboBox;
    private JLabel AppointmentIDLabel;
    private JLabel AppointmentStudentIDLabel;
    private JLabel AppointmentCounselorIDLabel;
    private JLabel AppointmentDateLabel;
    private JFormattedTextField AppointmentDateFormatedTextBox;
    private JComboBox AppointmentTimeComboBox;
    private JLabel AppointmentTimeLabel;
    private JTable table1;
    private JButton CounselorAddButton;
    private JButton CounselorUpdateButton;
    private JButton CounselorDeleteButton;
    private JLabel CounselorIDLabel;
    private JTextField CounselorIDTextBox;
    private JLabel CounselorNameLabel;
    private JTextField CounselorNameTextBox;
    private JLabel CounselorSpecializationLabel;
    private JComboBox CounselorSpecializationComboBox;
    private JLabel CounselorAvailabilityLabel;
    private JCheckBox CouselorMondayCheckBox;
    private JCheckBox CounselorTuesdayCheckBox;
    private JCheckBox CounselorWednessdayCheckBox;
    private JCheckBox CouselorThursdayCheckBox;
    private JCheckBox CouselorFridayCheckBox;
    private JTable FeedbackTable;
    private JButton FeedbackUpdateButton;
    private JButton FeedbackDeleteButton;
    private JLabel FeedbackIDLabel;
    private JTextField FedbackIDTextBox;
    private JLabel FeedbackAppointmentIDLabel;
    private JTextField FeedbackAppointIDTextBox;
    private JLabel FeedbackStudentNumberLabel;
    private JTextField FeedbackStudentNumberTextBox;
    private JLabel FeedbackRatingLabel;
    private JComboBox FeedbackRatingComboBox;
    private JLabel FeedbackCommentLabel;
    private JButton FeedbackAddButton;
    private JTextArea FeedbackCommentTextArea;
    private DefaultTableModel viewCounselorsTableModel;

    private UserRole currentUserRole;

    public MainDashboardPanel(UserRole role) {
        $$$setupUI$$$();
        this.currentUserRole = role;

        // --- Initialize JTable's Model and Data for MyAppointmentsTable ---
        if (MyAppointmentsTable != null) {
            String[] columnNames = {"Counselor", "Date", "Time", "Status"};
            myAppointmentsTableModel = new DefaultTableModel(columnNames, 0);
            MyAppointmentsTable.setModel(myAppointmentsTableModel);
            loadAppointmentsData();
        }

        // --- NEW: Initialize JTable's Model and Data for ViewCounselorsTable ---
        if (ViewCounselorsTable != null) {
            String[] counselorColumnNames = {"Name", "Specialization", "Availability", "Rating"};
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
        if (comp == ViewCounselorsTab) return "ViewCounselorsTab";
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
            if (ViewCounselorsTab != null) panelsToRemove.add(ViewCounselorsTab);
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel = new JTabbedPane();
        MainPanel.add(HomeTabbedPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        HomeTab = new JPanel();
        HomeTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTab.putClientProperty("html.disable", Boolean.FALSE);
        HomeTabbedPanel.addTab("Home", HomeTab);
        StudentManagementTab = new JPanel();
        StudentManagementTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("User Management", StudentManagementTab);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(27, 5, new Insets(0, 0, 0, 0), -1, -1));
        StudentManagementTab.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        UserNameTextBox = new JTextField();
        panel1.add(UserNameTextBox, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserSurnameTextBox = new JTextField();
        panel1.add(UserSurnameTextBox, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserEmailLabel = new JLabel();
        UserEmailLabel.setText("Email:");
        panel1.add(UserEmailLabel, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserSurnameLabel = new JLabel();
        UserSurnameLabel.setText("Surname:");
        panel1.add(UserSurnameLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserNameLabel = new JLabel();
        UserNameLabel.setText("Name:");
        panel1.add(UserNameLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserEmailTextBox = new JTextField();
        panel1.add(UserEmailTextBox, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserPhoneNumberTextBOx = new JTextField();
        panel1.add(UserPhoneNumberTextBOx, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserPhoneNumberLabel = new JLabel();
        UserPhoneNumberLabel.setText("Phone Number");
        panel1.add(UserPhoneNumberLabel, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserPasswordField = new JPasswordField();
        UserPasswordField.setEchoChar('*');
        panel1.add(UserPasswordField, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserPasswordLabel = new JLabel();
        UserPasswordLabel.setText("Password:");
        panel1.add(UserPasswordLabel, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserIDTextBox = new JTextField();
        panel1.add(UserIDTextBox, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserIDLabel = new JLabel();
        UserIDLabel.setText("User ID:");
        panel1.add(UserIDLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserUpdateButton = new JButton();
        UserUpdateButton.setText("Update");
        panel1.add(UserUpdateButton, new com.intellij.uiDesigner.core.GridConstraints(25, 1, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserDeleteButton = new JButton();
        UserDeleteButton.setText("Delete");
        panel1.add(UserDeleteButton, new com.intellij.uiDesigner.core.GridConstraints(25, 3, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserAddButton = new JButton();
        UserAddButton.setText("Add User");
        panel1.add(UserAddButton, new com.intellij.uiDesigner.core.GridConstraints(24, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(14, 1, 10, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 27, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        StudentsTable = new JTable();
        scrollPane1.setViewportView(StudentsTable);
        UserRoleLabel = new JLabel();
        UserRoleLabel.setText("User Role:");
        panel1.add(UserRoleLabel, new com.intellij.uiDesigner.core.GridConstraints(12, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserRoleComboBox = new JComboBox();
        panel1.add(UserRoleComboBox, new com.intellij.uiDesigner.core.GridConstraints(13, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentManagementTab = new JPanel();
        AppointmentManagementTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(14, 3, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Appointment Management", AppointmentManagementTab);
        AppointmentUpdateButton = new JButton();
        AppointmentUpdateButton.setText("Update");
        AppointmentManagementTab.add(AppointmentUpdateButton, new com.intellij.uiDesigner.core.GridConstraints(12, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentCounselorIDTextBox = new JTextField();
        AppointmentManagementTab.add(AppointmentCounselorIDTextBox, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentIDLabel = new JLabel();
        AppointmentIDLabel.setText("Appointment ID:");
        AppointmentManagementTab.add(AppointmentIDLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentIDTextBox = new JTextField();
        AppointmentManagementTab.add(AppointmentIDTextBox, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentStudentIDLabel = new JLabel();
        AppointmentStudentIDLabel.setText("Student ID");
        AppointmentManagementTab.add(AppointmentStudentIDLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentStudentIDTextBox = new JTextField();
        AppointmentManagementTab.add(AppointmentStudentIDTextBox, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentCounselorIDLabel = new JLabel();
        AppointmentCounselorIDLabel.setText("Counselor ID");
        AppointmentManagementTab.add(AppointmentCounselorIDLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentAddButton = new JButton();
        AppointmentAddButton.setText("Add Appointment");
        AppointmentManagementTab.add(AppointmentAddButton, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentDateLabel = new JLabel();
        AppointmentDateLabel.setText("Date");
        AppointmentManagementTab.add(AppointmentDateLabel, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentTimeLabel = new JLabel();
        AppointmentTimeLabel.setText("Time");
        AppointmentManagementTab.add(AppointmentTimeLabel, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        AppointmentManagementTab.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AppointmentDeleteButton = new JButton();
        AppointmentDeleteButton.setText("Delete");
        AppointmentManagementTab.add(AppointmentDeleteButton, new com.intellij.uiDesigner.core.GridConstraints(12, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        AppointmentManagementTab.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 14, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AppointmentTable = new JTable();
        scrollPane2.setViewportView(AppointmentTable);
        AppointmentDateFormatedTextBox = new JFormattedTextField();
        AppointmentManagementTab.add(AppointmentDateFormatedTextBox, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentTimeComboBox = new JComboBox();
        AppointmentManagementTab.add(AppointmentTimeComboBox, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorManagementTab = new JPanel();
        CounselorManagementTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(17, 3, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Counselor Management", CounselorManagementTab);
        final JScrollPane scrollPane3 = new JScrollPane();
        CounselorManagementTab.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 17, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane3.setViewportView(table1);
        CounselorAddButton = new JButton();
        CounselorAddButton.setText("Add Counselor");
        CounselorManagementTab.add(CounselorAddButton, new com.intellij.uiDesigner.core.GridConstraints(13, 1, 3, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorUpdateButton = new JButton();
        CounselorUpdateButton.setText("Update");
        CounselorManagementTab.add(CounselorUpdateButton, new com.intellij.uiDesigner.core.GridConstraints(16, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorDeleteButton = new JButton();
        CounselorDeleteButton.setText("Delete");
        CounselorManagementTab.add(CounselorDeleteButton, new com.intellij.uiDesigner.core.GridConstraints(16, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        CounselorManagementTab.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(12, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        CounselorIDLabel = new JLabel();
        CounselorIDLabel.setText("Counselor ID:");
        CounselorManagementTab.add(CounselorIDLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorIDTextBox = new JTextField();
        CounselorManagementTab.add(CounselorIDTextBox, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CounselorNameLabel = new JLabel();
        CounselorNameLabel.setText("Name:");
        CounselorManagementTab.add(CounselorNameLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorNameTextBox = new JTextField();
        CounselorManagementTab.add(CounselorNameTextBox, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CounselorSpecializationLabel = new JLabel();
        CounselorSpecializationLabel.setText("Specialization:");
        CounselorManagementTab.add(CounselorSpecializationLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorSpecializationComboBox = new JComboBox();
        CounselorManagementTab.add(CounselorSpecializationComboBox, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorAvailabilityLabel = new JLabel();
        CounselorAvailabilityLabel.setText("Availability:");
        CounselorManagementTab.add(CounselorAvailabilityLabel, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CouselorMondayCheckBox = new JCheckBox();
        CouselorMondayCheckBox.setText("Monday");
        CounselorManagementTab.add(CouselorMondayCheckBox, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorTuesdayCheckBox = new JCheckBox();
        CounselorTuesdayCheckBox.setText("Tuesday");
        CounselorManagementTab.add(CounselorTuesdayCheckBox, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorWednessdayCheckBox = new JCheckBox();
        CounselorWednessdayCheckBox.setText("Wednessday");
        CounselorManagementTab.add(CounselorWednessdayCheckBox, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CouselorThursdayCheckBox = new JCheckBox();
        CouselorThursdayCheckBox.setText("Thursday");
        CounselorManagementTab.add(CouselorThursdayCheckBox, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CouselorFridayCheckBox = new JCheckBox();
        CouselorFridayCheckBox.setText("Friday");
        CounselorManagementTab.add(CouselorFridayCheckBox, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackManagementTab = new JPanel();
        FeedbackManagementTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(16, 3, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Feedback Management", FeedbackManagementTab);
        final JScrollPane scrollPane4 = new JScrollPane();
        FeedbackManagementTab.add(scrollPane4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 16, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        FeedbackTable = new JTable();
        scrollPane4.setViewportView(FeedbackTable);
        FeedbackUpdateButton = new JButton();
        FeedbackUpdateButton.setText("Update");
        FeedbackManagementTab.add(FeedbackUpdateButton, new com.intellij.uiDesigner.core.GridConstraints(15, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackDeleteButton = new JButton();
        FeedbackDeleteButton.setText("Delete");
        FeedbackManagementTab.add(FeedbackDeleteButton, new com.intellij.uiDesigner.core.GridConstraints(15, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackIDLabel = new JLabel();
        FeedbackIDLabel.setText("Feedback ID:");
        FeedbackManagementTab.add(FeedbackIDLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FedbackIDTextBox = new JTextField();
        FeedbackManagementTab.add(FedbackIDTextBox, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FeedbackAppointmentIDLabel = new JLabel();
        FeedbackAppointmentIDLabel.setText("Appointment ID:");
        FeedbackManagementTab.add(FeedbackAppointmentIDLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackAppointIDTextBox = new JTextField();
        FeedbackManagementTab.add(FeedbackAppointIDTextBox, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FeedbackStudentNumberLabel = new JLabel();
        FeedbackStudentNumberLabel.setText("Student Number:");
        FeedbackManagementTab.add(FeedbackStudentNumberLabel, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackStudentNumberTextBox = new JTextField();
        FeedbackManagementTab.add(FeedbackStudentNumberTextBox, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 3, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FeedbackRatingLabel = new JLabel();
        FeedbackRatingLabel.setText("Rating");
        FeedbackManagementTab.add(FeedbackRatingLabel, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackRatingComboBox = new JComboBox();
        FeedbackManagementTab.add(FeedbackRatingComboBox, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackCommentLabel = new JLabel();
        FeedbackCommentLabel.setText("Comment:");
        FeedbackManagementTab.add(FeedbackCommentLabel, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackAddButton = new JButton();
        FeedbackAddButton.setText("Add Feedback");
        FeedbackManagementTab.add(FeedbackAddButton, new com.intellij.uiDesigner.core.GridConstraints(14, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane5 = new JScrollPane();
        FeedbackManagementTab.add(scrollPane5, new com.intellij.uiDesigner.core.GridConstraints(12, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        FeedbackCommentTextArea = new JTextArea();
        scrollPane5.setViewportView(FeedbackCommentTextArea);
        BookAppointmentTab = new JPanel();
        BookAppointmentTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Book Appointment", BookAppointmentTab);
        HeadingLabel = new JLabel();
        Font HeadingLabelFont = this.$$$getFont$$$(null, -1, 18, HeadingLabel.getFont());
        if (HeadingLabelFont != null) HeadingLabel.setFont(HeadingLabelFont);
        HeadingLabel.setText("Book new Appointment");
        BookAppointmentTab.add(HeadingLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorLabel = new JLabel();
        CounselorLabel.setText("Counselor:");
        BookAppointmentTab.add(CounselorLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DateLabel = new JLabel();
        DateLabel.setText("Date:");
        BookAppointmentTab.add(DateLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TimeLabel = new JLabel();
        TimeLabel.setText("Time:");
        BookAppointmentTab.add(TimeLabel, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BookAppointmentButton = new JButton();
        BookAppointmentButton.setText("Book Appointment");
        BookAppointmentTab.add(BookAppointmentButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorComboBox = new JComboBox();
        BookAppointmentTab.add(CounselorComboBox, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DateTextField = new JFormattedTextField();
        BookAppointmentTab.add(DateTextField, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        TimeComboBox = new JComboBox();
        BookAppointmentTab.add(TimeComboBox, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        BookAppointmentTab.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        MyAppointmentsTab = new JPanel();
        MyAppointmentsTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("My Appointments", MyAppointmentsTab);
        final JScrollPane scrollPane6 = new JScrollPane();
        MyAppointmentsTab.add(scrollPane6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        MyAppointmentsTable = new JTable();
        MyAppointmentsTable.setAutoCreateRowSorter(true);
        scrollPane6.setViewportView(MyAppointmentsTable);
        HeaderPanel = new JPanel();
        HeaderPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        MyAppointmentsTab.add(HeaderPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        RefreshButton = new JButton();
        RefreshButton.setText("Refresh");
        HeaderPanel.add(RefreshButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FilterTextBox = new JTextField();
        HeaderPanel.add(FilterTextBox, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FilterComboBox = new JComboBox();
        HeaderPanel.add(FilterComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FilterLabel = new JLabel();
        FilterLabel.setText("Filter:");
        HeaderPanel.add(FilterLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FooterPanel = new JPanel();
        FooterPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        MyAppointmentsTab.add(FooterPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PrintListButton = new JButton();
        PrintListButton.setText("Print List");
        FooterPanel.add(PrintListButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        FooterPanel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ViewCounselorsTab = new JPanel();
        ViewCounselorsTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("View Counselors", ViewCounselorsTab);
        ViewCouncelorsHeaderPanel = new JPanel();
        ViewCouncelorsHeaderPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        ViewCounselorsTab.add(ViewCouncelorsHeaderPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        SearchLabel = new JLabel();
        SearchLabel.setText("Search:");
        ViewCouncelorsHeaderPanel.add(SearchLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SearchTextBox = new JTextField();
        ViewCouncelorsHeaderPanel.add(SearchTextBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FilterByLabel = new JLabel();
        FilterByLabel.setText("Filter By:");
        ViewCouncelorsHeaderPanel.add(FilterByLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FilterByComboBox = new JComboBox();
        ViewCouncelorsHeaderPanel.add(FilterByComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane7 = new JScrollPane();
        ViewCounselorsTab.add(scrollPane7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ViewCounselorsTable = new JTable();
        scrollPane7.setViewportView(ViewCounselorsTable);
        SubmitFeedbackTab = new JPanel();
        SubmitFeedbackTab.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Submit Feedback", SubmitFeedbackTab);
        SubmitFeedbackHeader = new JLabel();
        Font SubmitFeedbackHeaderFont = this.$$$getFont$$$(null, -1, 18, SubmitFeedbackHeader.getFont());
        if (SubmitFeedbackHeaderFont != null) SubmitFeedbackHeader.setFont(SubmitFeedbackHeaderFont);
        SubmitFeedbackHeader.setText("Submit your Feedback");
        SubmitFeedbackTab.add(SubmitFeedbackHeader, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        SubmitFeedbackTab.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AppointmentLabel = new JLabel();
        AppointmentLabel.setText("Appointment:");
        SubmitFeedbackTab.add(AppointmentLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentComboBox = new JComboBox();
        SubmitFeedbackTab.add(AppointmentComboBox, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingLabel = new JLabel();
        RatingLabel.setText("Rating:");
        SubmitFeedbackTab.add(RatingLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton1 = new JRadioButton();
        RatingRadioButton1.setHorizontalTextPosition(10);
        RatingRadioButton1.setText("1");
        SubmitFeedbackTab.add(RatingRadioButton1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton2 = new JRadioButton();
        RatingRadioButton2.setHorizontalTextPosition(10);
        RatingRadioButton2.setText("2");
        SubmitFeedbackTab.add(RatingRadioButton2, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton3 = new JRadioButton();
        RatingRadioButton3.setHorizontalAlignment(10);
        RatingRadioButton3.setHorizontalTextPosition(10);
        RatingRadioButton3.setText("3");
        SubmitFeedbackTab.add(RatingRadioButton3, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton4 = new JRadioButton();
        RatingRadioButton4.setHorizontalAlignment(10);
        RatingRadioButton4.setHorizontalTextPosition(10);
        RatingRadioButton4.setText("4");
        SubmitFeedbackTab.add(RatingRadioButton4, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton5 = new JRadioButton();
        RatingRadioButton5.setHorizontalAlignment(10);
        RatingRadioButton5.setHorizontalTextPosition(10);
        RatingRadioButton5.setText("5");
        SubmitFeedbackTab.add(RatingRadioButton5, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CommentsLabel = new JLabel();
        CommentsLabel.setText("Comments");
        SubmitFeedbackTab.add(CommentsLabel, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane8 = new JScrollPane();
        SubmitFeedbackTab.add(scrollPane8, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        CommentsTextAreas = new JTextArea();
        scrollPane8.setViewportView(CommentsTextAreas);
        SubmitFeedbackButton = new JButton();
        SubmitFeedbackButton.setText("Submit Feedback");
        SubmitFeedbackTab.add(SubmitFeedbackButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

}