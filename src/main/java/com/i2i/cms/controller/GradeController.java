package com.i2i.cms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.ResponseGradeDto;
import com.i2i.cms.service.GradeService;

/**
 * <p>
 * Controller class handling endpoints related to student grades.
 * </p>
 */
@RestController
@RequestMapping("/cms/api/v1/students")
public class GradeController {
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
    @Autowired
    private GradeService gradeService;

    /**
     * <p>
     * Controller class handling endpoints related to student grades.
     * </p>
     */
    @GetMapping("/fetch-grade/{gradeId}")
    public ResponseEntity<?> getStudentsByGradeId(@PathVariable int gradeId) {
        try {
            logger.info("Retrieving students for grade ID {}", gradeId);
            List<ResponseGradeDto> students = gradeService.findStudentsByGradeId(gradeId);
            if (null == students || students.isEmpty()) {
                logger.warn("No students found for grade ID: {}", gradeId);
                return ResponseEntity.noContent().build();
            }
            logger.info("Retrieved students for grade ID: {}", gradeId);
            return ResponseEntity.ok(students);
        } catch (StudentException e) {
            logger.error("Error retrieving students for grade ID {}: {}", gradeId, e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
