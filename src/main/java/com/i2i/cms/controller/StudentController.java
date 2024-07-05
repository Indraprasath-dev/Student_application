package com.i2i.cms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateStudentDto;
import com.i2i.cms.dto.StudentInfoDto;
import com.i2i.cms.service.StudentService;

/**
 * <p>
 * Controller class handling endpoints related to students.
 * </p>
 */
@RestController
@RequestMapping("/cms/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    /**
     * <p>
     * Endpoint to add a new student.
     * </p>
     * Return CREATED status with the created StudentInfoDto on success, or INTERNAL_SERVER_ERROR on failure.
     */
    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@RequestBody CreateStudentDto createStudentDto) {
        try {
            logger.info("Adding student");
            StudentInfoDto studentInfoDto = studentService.addStudent(createStudentDto);
            logger.info("Student added successfully with ID: {}", studentInfoDto.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(studentInfoDto);
        } catch (StudentException e) {
            logger.error("Error adding student with name: {}", createStudentDto.getName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * <p>
     * Endpoint to fetch all students.
     * </p>
     * Return OK status with a list of StudentInfoDto objects on success, or INTERNAL_SERVER_ERROR on failure.
     */
    @GetMapping("/fetch-students")
    public ResponseEntity<?> fetchAllStudents() {
        try {
            logger.info("Fetching all students");
            List<StudentInfoDto> students = studentService.fetchAllStudents();
            logger.info("Fetched students");
            return ResponseEntity.ok(students);
        } catch (StudentException e) {
            logger.error("Error fetching all students", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * <p>
     * Endpoint to fetch a student by ID.
     * </p>
     * Return OK status with the StudentInfoDto if found, NOT_FOUND if no student found, or INTERNAL_SERVER_ERROR on failure.
     */
    @GetMapping("/fetch-student/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable int id) {
        try {
            logger.info("Fetching student with ID: {}", id);
            StudentInfoDto studentInfoDto = studentService.findStudentById(id);
            if (null == studentInfoDto) {
                logger.warn("Student with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            logger.info("Student with ID {} found", id);
            return ResponseEntity.ok(studentInfoDto);
        } catch (StudentException e) {
            logger.error("Error fetching student with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * <p>
     * Endpoint to delete a student by ID.
     * </p>
     * Return NO_CONTENT status if student deleted successfully, NOT_FOUND if no student found, or INTERNAL_SERVER_ERROR on failure.
     */
    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int id) {
        try {
            logger.info("Deleting student with ID: {}", id);
            boolean deleted = studentService.deleteStudentById(id);
            if (deleted) {
                logger.info("Student with ID {} deleted successfully", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Student with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (StudentException e) {
            logger.error("Error deleting student with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
