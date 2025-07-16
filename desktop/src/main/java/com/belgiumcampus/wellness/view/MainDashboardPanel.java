package com.belgiumcampus.wellness.view;

import com.belgiumcampus.wellness.util.UserRole;
import javax.swing.*;

public class MainDashboardPanel {

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
    private JTextField NameTextBox;
    private JTextField SurnameTextBox;
    private JButton DeleteButton;
    private JTextField AppointmentTimeTextBox;
    private JTable table1;
    private JLabel lbCounsID;
    private JTextField txtIdCouns;
    private JLabel lbname;
    private JTextField txtname;
    private JLabel lbSpes;
    private JTextField txtSpes;
    private JLabel lbAvail;
    private JTextField txtAvail;
    private JButton btnUpdateCouns;
    private JButton btnDeleteCouns;
    private JButton btnAddCouns;
    private JTable table2;
    private JLabel lbfeedID;
    private JTextField txtfeedID;
    private JLabel lbappointID;
    private JTextField txtAppoint;
    private JLabel lbstudentNr;
    private JTextField txtStudentNr;
    private JLabel lbRating;
    private JTextField txtRating;
    private JLabel lbComments;
    private JTextField textField1;
    private JButton btnSumitfeed;
    private JButton btnEditfeed;
    private JButton btnDeletefeed;
    private JTextField AppointmentDateTextBox;
    private JButton AddAppointmentButton;
    private JTextField StudentIDTextBox;
    private JTextField AppointmentIDTextBox;
    private JTextField CounselorIDTextBox;
    private JButton UpdateButton;
    private JButton AddStudent;
    private JButton Delete;
    private JButton Update;
    private JTextField StudentNumberTextBox;
    private JLabel PasswordTextBox;
    private JPasswordField passwordField1;
    private JTextField PhoneNumberTextBOx;
    private JTextField EmailTextBox;

    private UserRole currentUserRole; // To store the role

    /**The role of the user currently logged in (STUDENT or ADMIN).*/
    public MainDashboardPanel(UserRole role) {
        this.currentUserRole = role;

        // Adjust UI elements based on the user's role
        configureUIForRole();
    }

    public JPanel getRootPanel() {
        return MainPanel;
    }

    /**Configures the visibility and accessibility of UI elements based on thecurrent user's role.*/
    private void configureUIForRole() {
        if (currentUserRole == UserRole.STUDENT) {
            // For students, remove tabs that are primarily for admin management.

            // Remove Counselor Management tab for students
            int counselorTabIndex = HomeTabbedPanel.indexOfComponent(CounselorManagementTab);
            if (counselorTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(counselorTabIndex);
            }

            // Remove Counselor Management tab for students
            int stdentTabIndex = HomeTabbedPanel.indexOfComponent(StudentManagementTab);
            if (stdentTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(stdentTabIndex);
            }

            // Remove Feedback Management tab for students
            int feedbackTabIndex = HomeTabbedPanel.indexOfComponent(FeedbackManagementTab);
            if (feedbackTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(feedbackTabIndex);
            }

            // Remove Appointment Management tab for students
            int appointmentTabIndex = HomeTabbedPanel.indexOfComponent(AppointmentManagementTab);
            if (appointmentTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(appointmentTabIndex);
            }

        }
        if (currentUserRole == UserRole.ADMIN) {
            // For admins, remove tabs that are primarily for student management.

            // Remove Book Appointment tab for students
            int bookAppointmentTabIndex = HomeTabbedPanel.indexOfComponent(BookAppointmentTab);
            if (bookAppointmentTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(bookAppointmentTabIndex);
            }

            // Remove Book Appointment tab for students
            int myAppointmentsTabIndex = HomeTabbedPanel.indexOfComponent(MyAppointmentsTab);
            if (myAppointmentsTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(myAppointmentsTabIndex);
            }

            // Remove View Counselors tab for students
            int viewCounselorsTabIndex = HomeTabbedPanel.indexOfComponent(ViewCounselorsTab);
            if (viewCounselorsTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(viewCounselorsTabIndex);
            }

            // Remove Submit Feedback tab for students
            int submitFeedbackTabIndex = HomeTabbedPanel.indexOfComponent(SubmitFeedbackTab);
            if (submitFeedbackTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(submitFeedbackTabIndex);
            }

            // Remove Book Appointment tab for students
            int myFeedbackHistoryTabIndex = HomeTabbedPanel.indexOfComponent(MyFeedbackHistoryTab);
            if (myFeedbackHistoryTabIndex != -1) {
                HomeTabbedPanel.removeTabAt(myFeedbackHistoryTabIndex);
            }

        }
    }

}