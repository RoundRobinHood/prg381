package com.belgiumcampus.wellness.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends User {
    private String studentNumber; // PK

    public static final List<Student> AllStudents = new ArrayList<>();

    public Student(String studentNumber, String name, String surname, String email, String phone, String password) {
        super(name, surname, email, phone, password, "student"); // Role is fixed as "student"
        this.studentNumber = studentNumber;
    }

    // --- CRUD Operations (Local List) ---

    /**
     * Adds a new student to the AllStudents list.
     * @param studentNumber The unique student number.
     * @param name Student's name.
     * @param surname Student's surname.
     * @param email Student's email (unique for User).
     * @param phone Student's phone number.
     * @param password Student's password.
     * @return The newly created Student object, or null if a student with the same studentNumber already exists.
     */
    public static Student addStudent(String studentNumber, String name, String surname, String email, String phone, String password) {
        // Simple check for uniqueness of studentNumber (PK)
        for (Student s : AllStudents) {
            if (s.getStudentNumber().equals(studentNumber)) {
                System.err.println("Error: Student with number " + studentNumber + " already exists.");
                return null;
            }
        }
        Student newStudent = new Student(studentNumber, name, surname, email, phone, password);
        AllStudents.add(newStudent);
        return newStudent;
    }

    /**
     * Updates an existing student's information.
     * @param studentNumber The student number of the student to update.
     * @param newName New name.
     * @param newSurname New surname.
     * @param newEmail New email.
     * @param newPhone New phone number.
     * @param newPassword New password.
     * @return true if the student was found and updated, false otherwise.
     */
    public static boolean updateStudent(String studentNumber, String newName, String newSurname, String newEmail, String newPhone, String newPassword) {
        for (Student student : AllStudents) {
            if (student.getStudentNumber().equals(studentNumber)) {
                student.setName(newName);
                student.setSurname(newSurname);
                student.setEmail(newEmail); // Note: Changing unique email might need careful handling in a real DB
                student.setPhone(newPhone);
                student.setPassword(newPassword);
                return true;
            }
        }
        System.err.println("Error: Student with number " + studentNumber + " not found for update.");
        return false;
    }

    /**
     * Deletes a student from the AllStudents list.
     * @param studentNumber The student number of the student to delete.
     * @return true if the student was found and deleted, false otherwise.
     */
    public static boolean deleteStudent(String studentNumber) {
        Student studentToRemove = null;
        for (Student student : AllStudents) {
            if (student.getStudentNumber().equals(studentNumber)) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            AllStudents.remove(studentToRemove);
            return true;
        }
        System.err.println("Error: Student with number " + studentNumber + " not found for deletion.");
        return false;
    }

    // Getter
    public String getStudentNumber() {
        return studentNumber;
    }

    // Setter
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(studentNumber, student.studentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentNumber);
    }
}

