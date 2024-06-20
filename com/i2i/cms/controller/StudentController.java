package com.i2i.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.model.Student;
import com.i2i.cms.service.SportService;
import com.i2i.cms.service.StudentService;
import com.i2i.cms.util.DateUtil;
import com.i2i.cms.util.StringUtil;

/**
 * <p> 
 * This class is for managing student records. 
 * It performs operations such as displaying all student information, searching for students, 
 * adding and deleting students, and also assigning them to a particular 
 * standard and section.
 * </p>
 */
public class StudentController {
    private static Scanner scanner = new Scanner(System.in);
    private StudentService studentService = new StudentService();
    private SportService sportService = new SportService();

    /** 
     * <p>
     * Adds a new student record. 
     * This method collects the student's name, date of birth, class, section,
     * tuition fee, optional bus fee, hostel fee, and selected sports from the user,
     * and then creates a new student record.
     * </p> 
     */
    public void addStudent() {
        System.out.print("\nEnter student name: ");
        String name = scanner.next();
        while (!StringUtil.isValidName(name)) {
            System.out.print("Invalid name. Please enter a valid name: ");
            name = scanner.next();
        }
        System.out.print("Enter student date of birth (YYYY-MM-DD): ");
        String dob = scanner.next();
        Date dateOfBirth = DateUtil.ensureValidDate(dob);
        while (null == dateOfBirth) {
            System.out.print("Invalid date format. Please enter in YYYY-MM-DD format: ");
            dob = scanner.next();
            dateOfBirth = DateUtil.ensureValidDate(dob);
        }
        System.out.print("Enter the class : ");
        int standard = scanner.nextInt();
        System.out.print("Enter your Section (a, b, or c): ");
        String section = scanner.next();
        System.out.print("Enter your tuition fee : ");
        int tuitionFee = scanner.nextInt();
        System.out.println("Which fee do you want to enter?");
        int busFee = 0;
        int hostelFee = 0;
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. Bus fee");
            System.out.println("2. Hostel fee");
            int feeChoice = scanner.nextInt();
            switch (feeChoice) {
                case 1:
                    System.out.print("Enter the bus fee: ");
                    busFee = scanner.nextInt();
                    isValid = false;
                    break;
                case 2:
                    System.out.print("Enter the hostel fee: ");
                    hostelFee = scanner.nextInt();
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
        List<Integer> selectedSports = new ArrayList<>();
        boolean isSports = true;
        while (isSports) {
            System.out.println("Select your interested sports ");
            System.out.println("1. Cricket");
            System.out.println("2. Volleyball");
            System.out.println("3. Chess");
            System.out.println("4. Carrom");
            int sportChoice = scanner.nextInt();
            switch (sportChoice) {
                case 1:
                    System.out.println("You have selected Cricket.");
                    selectedSports.add(1);
                    break;
                case 2:
                    System.out.println("You have selected Volleyball.");
                    selectedSports.add(2);
                    break;
                case 3:
                    System.out.println("You have selected Chess.");
                    selectedSports.add(3);
                    break;
                case 4:
                    System.out.println("You have selected Carrom.");
                    selectedSports.add(4);
                    break;
                default:
                    System.out.println("Invalid choice");
                    continue;
            }
            System.out.print("\nDo you want to add another sport? (yes/no): ");
            String anotherSport = scanner.next();
            isSports = anotherSport.equalsIgnoreCase("yes");
        }
        try {
            Student student = studentService.addStudentWithFeeDetail(name, dateOfBirth, tuitionFee,
                    busFee, hostelFee, standard, section, selectedSports);
            if (null != student) {
                System.out.println("\nStudent added successfully! ID: " + student.getId()
                        + ", Name: " + student.getName());
            } else {
                System.out.println("\nFailed to add student.");
            }
        } catch (StudentException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** 
     * <p>
     * Retrieves and prints all student details.
     * </p> 
     */
    public void fetchAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (null != students) {
                for (Student student : students) {
                    System.out.println(student);
                }
            } else {
                System.out.println("No students found.");
            }
        } catch (StudentException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** 
     * <p>
     * Finds a student record by student ID. 
     * If the ID is not found, it prints an error message.
     * </p> 
     */
    public void searchStudentByStudentId() {
        System.out.println("\nEnter the student ID to search: ");
        while (true) {
            try {
                int studentId = scanner.nextInt();
                Student student = studentService.findStudentById(studentId);
                if (null != student) {
                    System.out.println(student);
                    break;
                } else {
                    System.out.println("Invalid student ID. Please enter a valid student ID.");
                }
            } catch (StudentException e) {
                System.out.println("Error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /** 
     * <p>
     * Updates the student name by student ID. 
     * If the ID is not found, it prints an error message.
     * </p> 
     */
    public void updateStudentById() {
        System.out.println("\nUpdating student name");
        System.out.print("\nEnter the Student ID : ");
        while (true) {
            int studentId = scanner.nextInt();
            System.out.print("Enter the Student name : ");
            String newName = scanner.next();
            while (!StringUtil.isValidName(newName)) {
                System.out.print("Invalid name. Please enter a valid name: ");
                newName = scanner.next();
            }
            try {
                Student student = studentService.updateStudentNameById(studentId, newName);
                if (null != student) {
                    System.out.println("Student name updated Successfully");
                    break;
                } else {
                    System.out.println("Invalid student ID. Please enter a valid student ID.");
                }
            } catch (StudentException e) {
                System.out.println("Error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /** 
     * <p>
     * Deletes the student record by student ID. 
     * If the ID is not found, it prints an error message.
     * </p> 
     */
    public void deleteStudentById() {
        System.out.println("\nEnter the student ID to delete: ");
        while (true) {
            try {
                int studentId = scanner.nextInt();
                boolean isDeleted = studentService.removeStudent(studentId);
                if (isDeleted) {
                    System.out.println("Student data Removed Successfully");
                    break;
                } else {
                    System.out.println("Invalid ID. Please enter a valid student ID.");
                }
            } catch (StudentException e) {
                System.out.println("Error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
