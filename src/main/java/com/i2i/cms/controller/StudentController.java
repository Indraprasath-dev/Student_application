package com.i2i.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
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
        logger.info("Starting addStudent process");
        System.out.print("\nEnter student name: ");
        String name = scanner.next();
        while (!StringUtil.isValidName(name)) {
            System.out.print("Invalid name. Please enter a valid name: ");
            logger.warn("Invalid name entered: {}", name);
            name = scanner.next();
        }
        logger.debug("Received input - Student Name: {}", name);
        System.out.print("Enter student date of birth (YYYY-MM-DD): ");
        String dob = scanner.next();
        Date dateOfBirth = DateUtil.ensureValidDate(dob);
        while (null == dateOfBirth) {
            System.out.print("Invalid date format. Please enter in YYYY-MM-DD format: ");
            logger.warn("Invalid date of birth entered: {}", dateOfBirth);
            dob = scanner.next();
            dateOfBirth = DateUtil.ensureValidDate(dob);
        }
        logger.debug("Received input - Date of Birth: {}", dob);
        System.out.print("Enter the class : ");
        int standard = scanner.nextInt();
        logger.debug("Received input - Standard: {}", standard);
        System.out.print("Enter your Section (a, b, or c): ");
        String section = scanner.next();
        logger.debug("Received input - Section: {}", section);
        System.out.print("Enter your tuition fee : ");
        int tuitionFee = scanner.nextInt();
        logger.debug("Received input - Tuition Fee: {}", tuitionFee);
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
        logger.debug("Received input - Bus Fee: {}, Hostel Fee: {}", busFee, hostelFee);
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
        logger.debug("Received input - Selected Sports: {}", selectedSports);
        try {
            Student student = studentService.addStudentWithFeeDetail(name, dateOfBirth, tuitionFee,
                    busFee, hostelFee, standard, section, selectedSports);
            if (null != student) {
                System.out.println("\nStudent added successfully! ID: " + student.getId()
                        + ", Name: " + student.getName());
                logger.info("Student added successfully. ID: {}, Name: {}", student.getId(), student.getName());
            } else {
                System.out.println("\nFailed to add student.");
                logger.warn("Failed to add student.");
            }
        } catch (StudentException e) {
            System.out.println("Error occurred while adding student: " + e.getMessage());
            logger.error("Error occurred while adding student: {}", e.getMessage());
        }
        logger.info("Finished addStudent process");
    }

    /** 
     * <p>
     * Retrieves and prints all student details.
     * </p> 
     */
    public void fetchAllStudents() {
        logger.info("Fetching all students");
        try {
            List<Student> students = studentService.getAllStudents();
            if (null != students) {
                for (Student student : students) {
                    System.out.println(student);
                    logger.debug("Found student: {}", student);
                }
                logger.info("Fetched all students");
            } else {
                System.out.println("No students found.");
                logger.warn("No students found.");
            }
        } catch (StudentException e) {
            System.out.println("Error occurred while fetching all students: " + e.getMessage());
            logger.error("Error occurred while fetching all students: {}", e.getMessage());
        }
        logger.info("Finished fetchAllStudents process");
    }

    /** 
     * <p>
     * Finds a student record by student ID. 
     * If the ID is not found, it prints an error message.
     * </p> 
     */
    public void searchStudentByStudentId() {
        logger.info("Starting searchStudentByStudentId process");
        System.out.println("\nEnter the student ID to search: ");
        while (true) {
            try {
                int studentId = scanner.nextInt();
                logger.debug("Received input - Student ID: {}", studentId);
                Student student = studentService.findStudentById(studentId);
                if (null != student) {
                    System.out.println(student);
                    logger.info("Fetched all students");
                    break;
                } else {
                    System.out.println("Invalid student ID. Please enter a valid student ID.");
                    logger.warn("Invalid student ID entered: {}", studentId);
                }
            } catch (StudentException e) {
                System.out.println("Error occurred while searching student by ID: " + e.getMessage());
                logger.error("Error occurred while searching student by ID: {}", e.getMessage());
            }
        }
        logger.info("Finished searchStudentByStudentId process");
    }

    /** 
     * <p>
     * Updates the student name by student ID. 
     * If the ID is not found, it prints an error message.
     * </p> 
     */
    public void updateStudentById() {
        logger.info("Starting updateStudentById process");
        System.out.println("\nUpdating student name");
        System.out.print("\nEnter the Student ID : ");
        while (true) {
            int studentId = scanner.nextInt();
            logger.debug("Received input - Student ID: {}", studentId);
            System.out.print("Enter the Student name : ");
            String newName = scanner.next();
            while (!StringUtil.isValidName(newName)) {
                System.out.print("Invalid name. Please enter a valid name: ");
                logger.warn("Invalid student name entered: {}", newName);
                newName = scanner.next();
            }
            logger.debug("Received input - New Student Name: {}", newName);
            try {
                Student student = studentService.updateStudentNameById(studentId, newName);
                if (null != student) {
                    System.out.println("Student name updated Successfully");
                    logger.info("Updated student name");
                    break;
                } else {
                    System.out.println("Invalid student ID. Please enter a valid student ID.");
                    logger.warn("Invalid student ID entered: {}", studentId);
                }
            } catch (StudentException e) {
                System.out.println("Error occurred while updating student name: " + e.getMessage());
                logger.error("Error occurred while updating student name: {}", e.getMessage());
            }
        }
        logger.info("Finished updateStudentById process");
    }

    /** 
     * <p>
     * Deletes the student record by student ID. 
     * If the ID is not found, it prints an error message.
     * </p> 
     */
    public void deleteStudentById() {
        logger.info("Starting deleteStudentById process");
        System.out.println("\nEnter the student ID to delete: ");
        while (true) {
            try {
                int studentId = scanner.nextInt();
                logger.debug("Received input - Student ID: {}", studentId);
                boolean isDeleted = studentService.removeStudent(studentId);
                if (isDeleted) {
                    System.out.println("Student data Removed Successfully");
                    logger.info("Student data removed");
                    break;
                } else {
                    System.out.println("Invalid ID. Please enter a valid student ID.");
                    logger.warn("Invalid student ID entered: {}", studentId);
                }
            } catch (StudentException e) {
                System.out.println("Error occurred while deleting student: " + e.getMessage());
                logger.error("Error occurred while deleting student: {}", e.getMessage());
            }
        }
        logger.info("Finished deleteStudentById process");
    }
}
