package com.i2i.cms.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.ResponseGradeDto;
import com.i2i.cms.service.GradeService;

/**
 * <p>
 * Controller class handling endpoints related to student grades.
 * </p>
 */
@RestController
@RequestMapping("/grades")
public class GradeController {
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
    @Autowired
    private GradeService gradeService;

    /**
     * <p>
     * GET endpoint to retrieve students by grade ID.
     * </p>
     * @param gradeId The UUID of the grade to retrieve students.
     * @return ResponseEntity containing a list of ResponseGradeDto objects representing students in the grade.
     */
    @GetMapping("/{gradeId}")
    public ResponseEntity<?> getStudentsByGradeId(@PathVariable UUID gradeId) {
        try {
            logger.info("Retrieving students for grade ID {}", gradeId);
            List<ResponseGradeDto> students = gradeService.findStudentsByGradeId(gradeId);
            if (null == students) {
                logger.warn("No students found for grade ID: {}", gradeId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not a valid ID");
            }
            logger.info("Retrieved students for grade ID: {}", gradeId);
            return ResponseEntity.ok(students);
        } catch (StudentException e) {
            logger.error("Error retrieving students for grade ID {}: {}", gradeId, e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
