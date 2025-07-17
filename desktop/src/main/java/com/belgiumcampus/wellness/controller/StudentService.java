package com.belgiumcampus.wellness.controller;

import com.belgiumcampus.wellness.classes.Student;

import java.util.List;

public class StudentService {

    /**
     * Adds a new student via the API (placeholder for now).
     * @param studentNumber The unique student number.
     * @param name Student's name.
     * @param surname Student's surname.
     * @param email Student's email.
     * @param phone Student's phone number.
     * @param password Student's password.
     * @return The created Student object, or null if creation failed.
     */
    public Student addStudent(String studentNumber, String name, String surname, String email, String phone, String password) {
        System.out.println("StudentService: Attempting to add student (API call placeholder)");
        // In the future, this will make an HTTP POST request to your API
        // For now, it calls the local storage method
        Student newStudent = Student.addStudent(studentNumber, name, surname, email, phone, password);
        if (newStudent != null) {
            System.out.println("StudentService: Student added locally - " + newStudent.getStudentNumber());
        }
        return newStudent;
    }

    /**
     * Retrieves a student by their student number via the API (placeholder for now).
     * @param studentNumber The unique student number.
     * @return The Student object, or null if not found.
     */
    public Student getStudentById(String studentNumber) {
        System.out.println("StudentService: Attempting to retrieve student by ID (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        // For now, it searches the local list
        for (Student student : Student.AllStudents) {
            if (student.getStudentNumber().equals(studentNumber)) {
                return student;
            }
        }
        System.out.println("StudentService: Student with ID " + studentNumber + " not found locally.");
        return null;
    }

    /**
     * Retrieves all students via the API (placeholder for now).
     * @return A list of all Student objects.
     */
    public List<Student> getAllStudents() {
        System.out.println("StudentService: Attempting to retrieve all students (API call placeholder)");
        // In the future, this will make an HTTP GET request to your API
        // For now, it returns the local list
        return Student.AllStudents;
    }

    /**
     * Updates an existing student's information via the API (placeholder for now).
     * @param studentNumber The student number of the student to update.
     * @param newName New name.
     * @param newSurname New surname.
     * @param newEmail New email.
     * @param newPhone New phone number.
     * @param newPassword New password.
     * @return true if the student was found and updated, false otherwise.
     */
    public boolean updateStudent(String studentNumber, String newName, String newSurname, String newEmail, String newPhone, String newPassword) {
        System.out.println("StudentService: Attempting to update student (API call placeholder)");
        // In the future, this will make an HTTP PUT request to your API
        return Student.updateStudent(studentNumber, newName, newSurname, newEmail, newPhone, newPassword);
    }

    /**
     * Deletes a student via the API (placeholder for now).
     * @param studentNumber The student number of the student to delete.
     * @return true if the student was found and deleted, false otherwise.
     */
    public boolean deleteStudent(String studentNumber) {
        System.out.println("StudentService: Attempting to delete student (API call placeholder)");
        // In the future, this will make an HTTP DELETE request to your API
        return Student.deleteStudent(studentNumber);
    }
}