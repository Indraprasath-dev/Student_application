package com.i2i.cms.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateStudentDto;
import com.i2i.cms.dto.StudentInfoDto;
import com.i2i.cms.dto.UpdateStudentDto;
import com.i2i.cms.service.StudentService;
import com.i2i.cms.util.DateUtil;
import com.i2i.cms.util.GradeUtil;
import com.i2i.cms.util.StringUtil;

/**
 * <p>
 * Controller class handling endpoints related to students.
 * </p>
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    
    /**
     * <p>
     * Endpoint to add a new student.
     * </p>
     * @param createStudentDto {@link CreateStudentDto}
     * @return CREATED status with the created StudentInfoDto on success, or INTERNAL_SERVER_ERROR on failure.
     */
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody CreateStudentDto createStudentDto) {
        try {
            if(!StringUtil.isValidName(createStudentDto.getName())) {
                return new ResponseEntity<>("Provide a valid name", HttpStatus.BAD_REQUEST);
            } else if(!DateUtil.isValidateDate(createStudentDto.getDob())){
                return new ResponseEntity<>("Provide a valid date", HttpStatus.BAD_REQUEST);
            } else if(!GradeUtil.isValidStandard(createStudentDto.getGrade().getStandard())) {
                return new ResponseEntity<>("Provide a valid standard", HttpStatus.BAD_REQUEST);
            } else if(!GradeUtil.isValidSection(createStudentDto.getGrade().getSection())) {
                return new ResponseEntity<>("Provide a valid section", HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Adding student");
                StudentInfoDto studentInfoDto = studentService.addStudent(createStudentDto);
                logger.info("Student added successfully with ID: {}", studentInfoDto.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(studentInfoDto);
            }
        } catch (StudentException e) {
            logger.error("Error adding student with name: {}", createStudentDto.getName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * <p>
     * Endpoint to fetch all students.
     * </p>
     * @return OK status with a list of StudentInfoDto objects on success, or INTERNAL_SERVER_ERROR on failure.
     */
    @GetMapping
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
     * @param id The id of the student to retrieve details.
     * @return OK status with the StudentInfoDto if found, NOT_FOUND if no student found, or INTERNAL_SERVER_ERROR
     * on failure.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable UUID id) {
        try {
            logger.info("Fetching student with ID: {}", id);
            StudentInfoDto studentInfoDto = studentService.findStudentById(id);
            if (null == studentInfoDto) {
                logger.warn("Student with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID ID");
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
     * Updates the information of a student based on the provided UpdateStudentDto.
     * </p>
     * @param id The id of the student to update details.
     * @param updateStudentDto The data transfer object containing updated student information.
     * @return A ResponseEntity containing the updated StudentInfoDto if successful, or an appropriate HTTP status and 
     * message if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable UUID id, @RequestBody UpdateStudentDto updateStudentDto) {
        try {
            updateStudentDto.setId(id);
            if(!StringUtil.isValidName(updateStudentDto.getName())) {
                return new ResponseEntity<>("Provide a valid name", HttpStatus.BAD_REQUEST);
            } else if(!DateUtil.isValidateDate(updateStudentDto.getDob())){
                return new ResponseEntity<>("Provide a valid date", HttpStatus.BAD_REQUEST);
            } else {
                StudentInfoDto studentInfoDto = studentService.updateStudentById(updateStudentDto);
                if (null == studentInfoDto) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not a valid ID");
                }
                return ResponseEntity.status(HttpStatus.OK).body(studentInfoDto);
            }
        } catch (StudentException e) {
            logger.error("Error updating the student", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * <p>
     * Endpoint to delete a student by ID.
     * </p>
     * @param id The id of the student to delete student detail.
     * @return NO_CONTENT status if student deleted successfully, NOT_FOUND if no student found, or INTERNAL_SERVER_ERROR on failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable UUID id) {
        try {
            logger.info("Deleting student with ID: {}", id);
            boolean deleted = studentService.deleteStudentById(id);
            if (deleted) {
                logger.info("Student with ID {} deleted successfully", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Student with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not a valid ID");
            }
        } catch (StudentException e) {
            logger.error("Error deleting student with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
