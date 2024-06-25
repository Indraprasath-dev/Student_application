package com.i2i.cms.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.model.Grade;
import com.i2i.cms.model.Student;
import com.i2i.cms.service.GradeService;

/**
 * <p>
 * This class is responsible for managing grade records. 
 * It provides functionality to add a new grade and search for students by grade ID.
 * </p>
 */
public class GradeController {
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
    private static Scanner scanner = new Scanner(System.in);
    private GradeService gradeService = new GradeService();
    
    /**
     * <p>
     * Adds a new grade record. 
     * This method collects the class and section from the user,
     * and then creates a new grade record.
     * </p>
     */
    public void addGrade() {
        logger.info("Starting addGrade process");
        System.out.println("\nEnter the class (standard): ");
        int standard = scanner.nextInt();        
        System.out.println("Enter the section (a, b, or c): ");
        String section = scanner.next();
        logger.debug("Received input - Standard: {}, Section: {}", standard, section);
        try {
            Grade grade = gradeService.addGradeId(standard, section);
            System.out.println("Grade added successfully with grade ID : " + grade.getGradeId());
            logger.info("Grade added successfully with grade ID : {}", grade.getGradeId());
        } catch(StudentException e) {
            System.out.println("Error occurred while adding grade : " + e.getMessage());
            logger.error("Error occurred while adding grade : {}", e.getMessage());
        }
        logger.info("Finished addGrade process");
    }
    
    /** 
     * <p>
     * Retrieves and prints the student details based on grade ID.
     * </p> 
     */
    public void searchStudentsByGradeId() {
        logger.info("Starting searchStudentsByGradeId process");
        System.out.println("\nEnter the grade ID : ");
        while(true) {
            try {
                int gradeId = scanner.nextInt();
                logger.debug("Received input - Grade ID: {}", gradeId);
                Grade grade = gradeService.findStudentsByGradeId(gradeId);
                if(null != grade) {
                    for (Student student : grade.getStudents()) {
                        System.out.println(student);
                        logger.debug("Found student: {}", student);
                    }
                    break;
                } else {
                    System.out.println("Invalid grade ID. Please enter a valid grade ID:");
                    logger.warn("Invalid grade ID entered: {}", gradeId);
                }
            } catch(StudentException e) {
                System.out.println("Error occurred while searching students by grade ID: " + e.getMessage());
                logger.error("Error occurred while searching students by grade ID: {}", e.getMessage());
            }
        }
        logger.info("Finished searchStudentsByGradeId process");
    }
}
