package com.i2i.cms.controller;

import java.util.Scanner;

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
        System.out.println("\nEnter the class (standard): ");
        int standard = scanner.nextInt();        
        System.out.println("Enter the section (a, b, or c): ");
        String section = scanner.next();
        try {
            Grade grade = gradeService.addGradeId(standard, section);
            System.out.println("Grade added successfully. Grade ID : " + grade.getGradeId());
        } catch(StudentException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /** 
     * <p>
     * Retrieves and prints the student details based on grade ID.
     * </p> 
     */
    public void searchStudentsByGradeId() {
        System.out.println("\nEnter the grade ID : ");
        while(true) {
            try {
                int gradeId = scanner.nextInt();
                Grade grade = gradeService.findStudentsByGradeId(gradeId);
                if(null != grade) {
                    for (Student student : grade.getStudents()) {
                        System.out.println(student);
                    }
                    break;
                } else {
                    System.out.println("Invalid grade ID. Please enter a valid grade ID.");    
                }
            } catch(StudentException e) {
                System.out.println("Error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
