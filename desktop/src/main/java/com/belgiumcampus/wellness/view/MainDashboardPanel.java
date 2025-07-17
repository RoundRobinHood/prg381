package com.belgiumcampus.wellness.view;

import com.belgiumcampus.wellness.util.UserRole;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        // add user
        UserAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = UserIDTextBox.getText().trim();
                String name = UserNameTextBox.getText().trim();
                String surname = UserSurnameTextBox.getText().trim();
                String email = UserEmailTextBox.getText().trim();
                String phone = UserPhoneNumberTextBOx.getText().trim();
                String password = new String(UserPasswordField.getPassword()).trim();
                String role = (String) UserRoleComboBox.getSelectedItem();
                if (userID.isEmpty() || name.isEmpty() || surname.isEmpty() || email.isEmpty() ||
                        phone.isEmpty() || password.isEmpty() || role == null) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                }else {
                    JOptionPane.showMessageDialog(null,
                            "User Added:\nID: " + userID +
                                    "\nName: " + name +
                                    "\nSurname: " + surname +
                                    "\nEmail: " + email +
                                    "\nPhone: " + phone +
                                    "\nRole: " + role);
                    clearUserFields();

                }


            }
        });

        CounselorUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCounselor();

            }
        });
        CounselorDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCounselor();

            }
        });
        //appointment add
        AppointmentAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentID = AppointmentIDTextBox.getText().trim();
                String studentID = AppointmentStudentIDTextBox.getText().trim();
                String counselorID = AppointmentCounselorIDTextBox.getText().trim();
                String date = AppointmentDateFormatedTextBox.getText().trim();
                String time = (String) AppointmentTimeComboBox.getSelectedItem();

                if (appointmentID.isEmpty() || counselorID.isEmpty() || studentID.isEmpty() || date.isEmpty() || time == null) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Appointment Added:\nID: " + appointmentID +
                                    "\nCounselor: " + counselorID +
                                    "\nStudent: " + studentID +
                                    "\nDate: " + date +
                                    "\nTime: " + time);

                    clearFields();


                }
            }

        });
// appointment update
        AppointmentUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentId = AppointmentIDTextBox.getText().trim();
                if (appointmentId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Appointment ID is required for update!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String studentID = AppointmentStudentIDTextBox.getText().trim();
                String counselorID = AppointmentCounselorIDTextBox.getText().trim();
                String date = AppointmentDateFormatedTextBox.getText().trim();
                String time = (String) AppointmentTimeComboBox.getSelectedItem();
                if (studentID.isEmpty() || counselorID.isEmpty() || date.isEmpty() || time == null) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("Update Appointment: ID=" + appointmentId + ", Student=" + studentID + ", Counselor=" + counselorID + ", Date=" + date + ", Time=" + time);
                JOptionPane.showMessageDialog(null, "Appointment updated successfully!");

                clearFields();

            }
        });
        // add counselor button
        CounselorAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCounselor();
            }
        });
        //delete appointment button
        AppointmentDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = AppointmentIDTextBox.getText().trim();

                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Appointment ID is required for deletion!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int appointmentId;
                try {
                    appointmentId = Integer.parseInt(idText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Appointment ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("Delete Appointment: ID=" + appointmentId);
                JOptionPane.showMessageDialog(null, "Appointment with ID " + appointmentId + " deleted successfully!");
                clearFields();

            }
        });
        // update user
        UserUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = UserIDTextBox.getText().trim();

                if (userID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter User ID to update.");
                    return;
                }
                String name = UserNameTextBox.getText().trim();
                String surname = UserSurnameTextBox.getText().trim();
                String email = UserEmailTextBox.getText().trim();
                String phone = UserPhoneNumberTextBOx.getText().trim();
                String password = new String(UserPasswordField.getPassword()).trim();
                String role = (String) UserRoleComboBox.getSelectedItem();

                if (name.isEmpty() || surname.isEmpty() || email.isEmpty() ||
                        phone.isEmpty() || password.isEmpty() || role == null) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "User Updated:\nID: " + userID +
                                    "\nName: " + name +
                                    "\nSurname: " + surname +
                                    "\nEmail: " + email +
                                    "\nPhone: " + phone +
                                    "\nRole: " + role);
                    clearUserFields();

                }
            }
        });
        // delete User
        UserDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = UserIDTextBox.getText().trim();

                if (userID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter User ID to delete.");
                } else {
                    JOptionPane.showMessageDialog(null, "User with ID " + userID + " deleted.");
                    clearUserFields();
                }
            }
        });
// Add your feedback
        FeedbackAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feedbackID = FedbackIDTextBox.getText().trim();
                String appointmentID = FeedbackAppointIDTextBox.getText().trim();
                String studentNumber = FeedbackStudentNumberTextBox.getText().trim();
                String rating = (String)FeedbackRatingComboBox.getSelectedItem();
                String comment = FeedbackCommentTextArea.getText().trim();
                if (feedbackID.isEmpty() || appointmentID.isEmpty() || studentNumber.isEmpty() || rating == null || comment.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Feedback Added:\nFeedback ID: " + feedbackID +
                                    "\nAppointment ID: " + appointmentID +
                                    "\nStudent Number: " + studentNumber +
                                    "\nRating: " + rating +
                                    "\nComment: " + comment);
                    clearFeedbackFields();

                }
            }
        });
        //Update Feedback
        FeedbackUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feedbackID = FedbackIDTextBox.getText().trim();

                if (feedbackID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Feedback ID to update.");
                    return;
                }

                String appointmentID = FeedbackAppointIDTextBox.getText().trim();
                String studentNumber = FeedbackStudentNumberTextBox.getText().trim();
                String rating = (String) FeedbackRatingComboBox.getSelectedItem();
                String comment = FeedbackCommentTextArea.getText().trim();

                if (appointmentID.isEmpty() || studentNumber.isEmpty() || rating == null || comment.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Feedback Updated:\nFeedback ID: " + feedbackID +
                                    "\nAppointment ID: " + appointmentID +
                                    "\nStudent Number: " + studentNumber +
                                    "\nRating: " + rating +
                                    "\nComment: " + comment);
                    clearFeedbackFields();

                }
            }
        });
        // Delete Feedback
        FeedbackDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feedbackID = FedbackIDTextBox.getText().trim();

                if (feedbackID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Feedback ID to delete.");
                } else {
                    JOptionPane.showMessageDialog(null, "Feedback with ID " + feedbackID + " deleted.");
                    clearFeedbackFields();
                }
            }
        });
    }
    private void clearFeedbackFields() {
        FedbackIDTextBox.setText("");
        FeedbackAppointIDTextBox.setText("");
        FeedbackStudentNumberTextBox.setText("");
        FeedbackRatingComboBox.setSelectedIndex(0);
        FeedbackCommentTextArea.setText("");
    }
    private void clearUserFields() {
        UserIDTextBox.setText("");
        UserNameTextBox.setText("");
        UserSurnameTextBox.setText("");
        UserEmailTextBox.setText("");
        UserPhoneNumberTextBOx.setText("");
        UserPasswordField.setText("");
        UserRoleComboBox.setSelectedIndex(0);
    }
    private void clearFields() {
        AppointmentIDTextBox.setText("");
        AppointmentStudentIDTextBox.setText("");
        AppointmentCounselorIDTextBox.setText("");
        AppointmentDateFormatedTextBox.setText("");
        AppointmentTimeComboBox.setSelectedIndex(0); // Resets to first item in combo box
    }
// add counselor
    private void addCounselor() {
        String name = CounselorNameTextBox.getText().trim();
        String specialization = (String) CounselorSpecializationComboBox.getSelectedItem();
        StringBuilder availability = new StringBuilder();
        if (CouselorMondayCheckBox.isSelected()) availability.append("Monday,");
        if (CounselorTuesdayCheckBox.isSelected()) availability.append("Tuesday,");
        if (CounselorWednessdayCheckBox.isSelected()) availability.append("Wednesday,");
        if (CouselorThursdayCheckBox.isSelected()) availability.append("Thursday,");
        if (CouselorFridayCheckBox.isSelected()) availability.append("Friday,");
        String availabilityStr = availability.toString().replaceAll(",$", "");
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (specialization == null || specialization.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a specialization!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (availabilityStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select at least one day!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Counselor Added: " + name + ", " + specialization + ", " + availabilityStr);
        JOptionPane.showMessageDialog(null, "Counselor added successfully!");
    }
// update Counselor
    private void updateCounselor() {
        String idText = CounselorIDTextBox.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Counselor ID is required for update!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int counselorId;
        try {
            counselorId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Counselor ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String name = CounselorNameTextBox.getText().trim();
        String specialization = (String) CounselorSpecializationComboBox.getSelectedItem();

        StringBuilder availability = new StringBuilder();
        if (CouselorMondayCheckBox.isSelected()) availability.append("Monday,");
        if (CounselorTuesdayCheckBox.isSelected()) availability.append("Tuesday,");
        if (CounselorWednessdayCheckBox.isSelected()) availability.append("Wednesday,");
        if (CouselorThursdayCheckBox.isSelected()) availability.append("Thursday,");
        if (CouselorFridayCheckBox.isSelected()) availability.append("Friday,");
        String availabilityStr = availability.toString().replaceAll(",$", "");

        if (name.isEmpty() || specialization.isEmpty() || availabilityStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Update Counselor: ID=" + counselorId + ", " + name + ", " + specialization + ", " + availabilityStr);
        JOptionPane.showMessageDialog(null, "Counselor updated successfully!");
    }
// delete Counselor
    private void deleteCounselor() {
        String idText = CounselorIDTextBox.getText().trim();
        int counselorId;
        try {
            counselorId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Counselor ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Delete Counselor: ID=" + counselorId);
        JOptionPane.showMessageDialog(null, "Counselor deleted successfully!");
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
        MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel = new JTabbedPane();
        MainPanel.add(HomeTabbedPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        HomeTab = new JPanel();
        HomeTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTab.putClientProperty("html.disable", Boolean.FALSE);
        HomeTabbedPanel.addTab("Home", HomeTab);
        StudentManagementTab = new JPanel();
        StudentManagementTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("User Management", StudentManagementTab);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(27, 5, new Insets(0, 0, 0, 0), -1, -1));
        StudentManagementTab.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        UserNameTextBox = new JTextField();
        panel1.add(UserNameTextBox, new GridConstraints(3, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserSurnameTextBox = new JTextField();
        panel1.add(UserSurnameTextBox, new GridConstraints(5, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserEmailLabel = new JLabel();
        UserEmailLabel.setText("Email:");
        panel1.add(UserEmailLabel, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserSurnameLabel = new JLabel();
        UserSurnameLabel.setText("Surname:");
        panel1.add(UserSurnameLabel, new GridConstraints(4, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserNameLabel = new JLabel();
        UserNameLabel.setText("Name:");
        panel1.add(UserNameLabel, new GridConstraints(2, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserEmailTextBox = new JTextField();
        panel1.add(UserEmailTextBox, new GridConstraints(7, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserPhoneNumberTextBOx = new JTextField();
        panel1.add(UserPhoneNumberTextBOx, new GridConstraints(9, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserPhoneNumberLabel = new JLabel();
        UserPhoneNumberLabel.setText("Phone Number");
        panel1.add(UserPhoneNumberLabel, new GridConstraints(8, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserPasswordField = new JPasswordField();
        UserPasswordField.setEchoChar('*');
        panel1.add(UserPasswordField, new GridConstraints(11, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserPasswordLabel = new JLabel();
        UserPasswordLabel.setText("Password:");
        panel1.add(UserPasswordLabel, new GridConstraints(10, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserIDTextBox = new JTextField();
        panel1.add(UserIDTextBox, new GridConstraints(1, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UserIDLabel = new JLabel();
        UserIDLabel.setText("User ID:");
        panel1.add(UserIDLabel, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserUpdateButton = new JButton();
        UserUpdateButton.setText("Update");
        panel1.add(UserUpdateButton, new GridConstraints(25, 1, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserDeleteButton = new JButton();
        UserDeleteButton.setText("Delete");
        panel1.add(UserDeleteButton, new GridConstraints(25, 3, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserAddButton = new JButton();
        UserAddButton.setText("Add User");
        panel1.add(UserAddButton, new GridConstraints(24, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(14, 1, 10, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 27, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        StudentsTable = new JTable();
        scrollPane1.setViewportView(StudentsTable);
        UserRoleLabel = new JLabel();
        UserRoleLabel.setText("User Role:");
        panel1.add(UserRoleLabel, new GridConstraints(12, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UserRoleComboBox = new JComboBox();
        panel1.add(UserRoleComboBox, new GridConstraints(13, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentManagementTab = new JPanel();
        AppointmentManagementTab.setLayout(new GridLayoutManager(14, 3, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Appointment Management", AppointmentManagementTab);
        AppointmentUpdateButton = new JButton();
        AppointmentUpdateButton.setText("Update");
        AppointmentManagementTab.add(AppointmentUpdateButton, new GridConstraints(12, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentCounselorIDTextBox = new JTextField();
        AppointmentManagementTab.add(AppointmentCounselorIDTextBox, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentIDLabel = new JLabel();
        AppointmentIDLabel.setText("Appointment ID:");
        AppointmentManagementTab.add(AppointmentIDLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentIDTextBox = new JTextField();
        AppointmentManagementTab.add(AppointmentIDTextBox, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentStudentIDLabel = new JLabel();
        AppointmentStudentIDLabel.setText("Student ID");
        AppointmentManagementTab.add(AppointmentStudentIDLabel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentStudentIDTextBox = new JTextField();
        AppointmentManagementTab.add(AppointmentStudentIDTextBox, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentCounselorIDLabel = new JLabel();
        AppointmentCounselorIDLabel.setText("Counselor ID");
        AppointmentManagementTab.add(AppointmentCounselorIDLabel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentAddButton = new JButton();
        AppointmentAddButton.setText("Add Appointment");
        AppointmentManagementTab.add(AppointmentAddButton, new GridConstraints(11, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentDateLabel = new JLabel();
        AppointmentDateLabel.setText("Date");
        AppointmentManagementTab.add(AppointmentDateLabel, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentTimeLabel = new JLabel();
        AppointmentTimeLabel.setText("Time");
        AppointmentManagementTab.add(AppointmentTimeLabel, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        AppointmentManagementTab.add(spacer2, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AppointmentDeleteButton = new JButton();
        AppointmentDeleteButton.setText("Delete");
        AppointmentManagementTab.add(AppointmentDeleteButton, new GridConstraints(12, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        AppointmentManagementTab.add(scrollPane2, new GridConstraints(0, 0, 14, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AppointmentTable = new JTable();
        scrollPane2.setViewportView(AppointmentTable);
        AppointmentDateFormatedTextBox = new JFormattedTextField();
        AppointmentManagementTab.add(AppointmentDateFormatedTextBox, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AppointmentTimeComboBox = new JComboBox();
        AppointmentManagementTab.add(AppointmentTimeComboBox, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorManagementTab = new JPanel();
        CounselorManagementTab.setLayout(new GridLayoutManager(17, 3, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Counselor Management", CounselorManagementTab);
        final JScrollPane scrollPane3 = new JScrollPane();
        CounselorManagementTab.add(scrollPane3, new GridConstraints(0, 0, 17, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane3.setViewportView(table1);
        CounselorAddButton = new JButton();
        CounselorAddButton.setText("Add Counselor");
        CounselorManagementTab.add(CounselorAddButton, new GridConstraints(13, 1, 3, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorUpdateButton = new JButton();
        CounselorUpdateButton.setText("Update");
        CounselorManagementTab.add(CounselorUpdateButton, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorDeleteButton = new JButton();
        CounselorDeleteButton.setText("Delete");
        CounselorManagementTab.add(CounselorDeleteButton, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        CounselorManagementTab.add(spacer3, new GridConstraints(12, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        CounselorIDLabel = new JLabel();
        CounselorIDLabel.setText("Counselor ID:");
        CounselorManagementTab.add(CounselorIDLabel, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorIDTextBox = new JTextField();
        CounselorManagementTab.add(CounselorIDTextBox, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CounselorNameLabel = new JLabel();
        CounselorNameLabel.setText("Name:");
        CounselorManagementTab.add(CounselorNameLabel, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorNameTextBox = new JTextField();
        CounselorManagementTab.add(CounselorNameTextBox, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CounselorSpecializationLabel = new JLabel();
        CounselorSpecializationLabel.setText("Specialization:");
        CounselorManagementTab.add(CounselorSpecializationLabel, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorSpecializationComboBox = new JComboBox();
        CounselorManagementTab.add(CounselorSpecializationComboBox, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorAvailabilityLabel = new JLabel();
        CounselorAvailabilityLabel.setText("Availability:");
        CounselorManagementTab.add(CounselorAvailabilityLabel, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CouselorMondayCheckBox = new JCheckBox();
        CouselorMondayCheckBox.setText("Monday");
        CounselorManagementTab.add(CouselorMondayCheckBox, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorTuesdayCheckBox = new JCheckBox();
        CounselorTuesdayCheckBox.setText("Tuesday");
        CounselorManagementTab.add(CounselorTuesdayCheckBox, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorWednessdayCheckBox = new JCheckBox();
        CounselorWednessdayCheckBox.setText("Wednessday");
        CounselorManagementTab.add(CounselorWednessdayCheckBox, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CouselorThursdayCheckBox = new JCheckBox();
        CouselorThursdayCheckBox.setText("Thursday");
        CounselorManagementTab.add(CouselorThursdayCheckBox, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CouselorFridayCheckBox = new JCheckBox();
        CouselorFridayCheckBox.setText("Friday");
        CounselorManagementTab.add(CouselorFridayCheckBox, new GridConstraints(11, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackManagementTab = new JPanel();
        FeedbackManagementTab.setLayout(new GridLayoutManager(16, 3, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Feedback Management", FeedbackManagementTab);
        final JScrollPane scrollPane4 = new JScrollPane();
        FeedbackManagementTab.add(scrollPane4, new GridConstraints(0, 0, 16, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        FeedbackTable = new JTable();
        scrollPane4.setViewportView(FeedbackTable);
        FeedbackUpdateButton = new JButton();
        FeedbackUpdateButton.setText("Update");
        FeedbackManagementTab.add(FeedbackUpdateButton, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackDeleteButton = new JButton();
        FeedbackDeleteButton.setText("Delete");
        FeedbackManagementTab.add(FeedbackDeleteButton, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackIDLabel = new JLabel();
        FeedbackIDLabel.setText("Feedback ID:");
        FeedbackManagementTab.add(FeedbackIDLabel, new GridConstraints(0, 1, 2, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FedbackIDTextBox = new JTextField();
        FeedbackManagementTab.add(FedbackIDTextBox, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FeedbackAppointmentIDLabel = new JLabel();
        FeedbackAppointmentIDLabel.setText("Appointment ID:");
        FeedbackManagementTab.add(FeedbackAppointmentIDLabel, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackAppointIDTextBox = new JTextField();
        FeedbackManagementTab.add(FeedbackAppointIDTextBox, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FeedbackStudentNumberLabel = new JLabel();
        FeedbackStudentNumberLabel.setText("Student Number:");
        FeedbackManagementTab.add(FeedbackStudentNumberLabel, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackStudentNumberTextBox = new JTextField();
        FeedbackManagementTab.add(FeedbackStudentNumberTextBox, new GridConstraints(6, 1, 3, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FeedbackRatingLabel = new JLabel();
        FeedbackRatingLabel.setText("Rating");
        FeedbackManagementTab.add(FeedbackRatingLabel, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackRatingComboBox = new JComboBox();
        FeedbackManagementTab.add(FeedbackRatingComboBox, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackCommentLabel = new JLabel();
        FeedbackCommentLabel.setText("Comment:");
        FeedbackManagementTab.add(FeedbackCommentLabel, new GridConstraints(11, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FeedbackAddButton = new JButton();
        FeedbackAddButton.setText("Add Feedback");
        FeedbackManagementTab.add(FeedbackAddButton, new GridConstraints(14, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane5 = new JScrollPane();
        FeedbackManagementTab.add(scrollPane5, new GridConstraints(12, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        FeedbackCommentTextArea = new JTextArea();
        scrollPane5.setViewportView(FeedbackCommentTextArea);
        BookAppointmentTab = new JPanel();
        BookAppointmentTab.setLayout(new GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Book Appointment", BookAppointmentTab);
        HeadingLabel = new JLabel();
        Font HeadingLabelFont = this.$$$getFont$$$(null, -1, 18, HeadingLabel.getFont());
        if (HeadingLabelFont != null) HeadingLabel.setFont(HeadingLabelFont);
        HeadingLabel.setText("Book new Appointment");
        BookAppointmentTab.add(HeadingLabel, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorLabel = new JLabel();
        CounselorLabel.setText("Counselor:");
        BookAppointmentTab.add(CounselorLabel, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DateLabel = new JLabel();
        DateLabel.setText("Date:");
        BookAppointmentTab.add(DateLabel, new GridConstraints(3, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TimeLabel = new JLabel();
        TimeLabel.setText("Time:");
        BookAppointmentTab.add(TimeLabel, new GridConstraints(5, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BookAppointmentButton = new JButton();
        BookAppointmentButton.setText("Book Appointment");
        BookAppointmentTab.add(BookAppointmentButton, new GridConstraints(8, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CounselorComboBox = new JComboBox();
        BookAppointmentTab.add(CounselorComboBox, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DateTextField = new JFormattedTextField();
        BookAppointmentTab.add(DateTextField, new GridConstraints(4, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        TimeComboBox = new JComboBox();
        BookAppointmentTab.add(TimeComboBox, new GridConstraints(6, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        BookAppointmentTab.add(spacer4, new GridConstraints(7, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        MyAppointmentsTab = new JPanel();
        MyAppointmentsTab.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("My Appointments", MyAppointmentsTab);
        final JScrollPane scrollPane6 = new JScrollPane();
        MyAppointmentsTab.add(scrollPane6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        MyAppointmentsTable = new JTable();
        MyAppointmentsTable.setAutoCreateRowSorter(true);
        scrollPane6.setViewportView(MyAppointmentsTable);
        HeaderPanel = new JPanel();
        HeaderPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        MyAppointmentsTab.add(HeaderPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        RefreshButton = new JButton();
        RefreshButton.setText("Refresh");
        HeaderPanel.add(RefreshButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FilterTextBox = new JTextField();
        HeaderPanel.add(FilterTextBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FilterComboBox = new JComboBox();
        HeaderPanel.add(FilterComboBox, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FilterLabel = new JLabel();
        FilterLabel.setText("Filter:");
        HeaderPanel.add(FilterLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FooterPanel = new JPanel();
        FooterPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        MyAppointmentsTab.add(FooterPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PrintListButton = new JButton();
        PrintListButton.setText("Print List");
        FooterPanel.add(PrintListButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        FooterPanel.add(spacer5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ViewCounselorsTab = new JPanel();
        ViewCounselorsTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("View Counselors", ViewCounselorsTab);
        ViewCouncelorsHeaderPanel = new JPanel();
        ViewCouncelorsHeaderPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        ViewCounselorsTab.add(ViewCouncelorsHeaderPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        SearchLabel = new JLabel();
        SearchLabel.setText("Search:");
        ViewCouncelorsHeaderPanel.add(SearchLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SearchTextBox = new JTextField();
        ViewCouncelorsHeaderPanel.add(SearchTextBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        FilterByLabel = new JLabel();
        FilterByLabel.setText("Filter By:");
        ViewCouncelorsHeaderPanel.add(FilterByLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FilterByComboBox = new JComboBox();
        ViewCouncelorsHeaderPanel.add(FilterByComboBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane7 = new JScrollPane();
        ViewCounselorsTab.add(scrollPane7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ViewCounselorsTable = new JTable();
        scrollPane7.setViewportView(ViewCounselorsTable);
        SubmitFeedbackTab = new JPanel();
        SubmitFeedbackTab.setLayout(new GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        HomeTabbedPanel.addTab("Submit Feedback", SubmitFeedbackTab);
        SubmitFeedbackHeader = new JLabel();
        Font SubmitFeedbackHeaderFont = this.$$$getFont$$$(null, -1, 18, SubmitFeedbackHeader.getFont());
        if (SubmitFeedbackHeaderFont != null) SubmitFeedbackHeader.setFont(SubmitFeedbackHeaderFont);
        SubmitFeedbackHeader.setText("Submit your Feedback");
        SubmitFeedbackTab.add(SubmitFeedbackHeader, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        SubmitFeedbackTab.add(spacer6, new GridConstraints(7, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AppointmentLabel = new JLabel();
        AppointmentLabel.setText("Appointment:");
        SubmitFeedbackTab.add(AppointmentLabel, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AppointmentComboBox = new JComboBox();
        SubmitFeedbackTab.add(AppointmentComboBox, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingLabel = new JLabel();
        RatingLabel.setText("Rating:");
        SubmitFeedbackTab.add(RatingLabel, new GridConstraints(3, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton1 = new JRadioButton();
        RatingRadioButton1.setHorizontalTextPosition(10);
        RatingRadioButton1.setText("1");
        SubmitFeedbackTab.add(RatingRadioButton1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton2 = new JRadioButton();
        RatingRadioButton2.setHorizontalTextPosition(10);
        RatingRadioButton2.setText("2");
        SubmitFeedbackTab.add(RatingRadioButton2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton3 = new JRadioButton();
        RatingRadioButton3.setHorizontalAlignment(10);
        RatingRadioButton3.setHorizontalTextPosition(10);
        RatingRadioButton3.setText("3");
        SubmitFeedbackTab.add(RatingRadioButton3, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton4 = new JRadioButton();
        RatingRadioButton4.setHorizontalAlignment(10);
        RatingRadioButton4.setHorizontalTextPosition(10);
        RatingRadioButton4.setText("4");
        SubmitFeedbackTab.add(RatingRadioButton4, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RatingRadioButton5 = new JRadioButton();
        RatingRadioButton5.setHorizontalAlignment(10);
        RatingRadioButton5.setHorizontalTextPosition(10);
        RatingRadioButton5.setText("5");
        SubmitFeedbackTab.add(RatingRadioButton5, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CommentsLabel = new JLabel();
        CommentsLabel.setText("Comments");
        SubmitFeedbackTab.add(CommentsLabel, new GridConstraints(5, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane8 = new JScrollPane();
        SubmitFeedbackTab.add(scrollPane8, new GridConstraints(6, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        CommentsTextAreas = new JTextArea();
        scrollPane8.setViewportView(CommentsTextAreas);
        SubmitFeedbackButton = new JButton();
        SubmitFeedbackButton.setText("Submit Feedback");
        SubmitFeedbackTab.add(SubmitFeedbackButton, new GridConstraints(8, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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