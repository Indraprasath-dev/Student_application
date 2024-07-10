package com.i2i.cms.service;

import java.util.List;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.ResponseGradeDto;
import com.i2i.cms.model.Grade;

/**
 * <p>
 * Interface for Grade Service operations.
 * Provides methods to add grades, find students by grade ID, and map students to ResponseGradeDto.
 * </p>
 */
public interface GradeServiceInterface {

    /**
     * <p>
     * Adds a new grade with the specified standard and section.
     * </p>
     * @param standard the standard of the grade (1 to 12)
     * @param section the section of the grade (A, B, or C)
     * @return the added Grade object
     * @throws StudentException if there is an error adding the grade
     */
    Grade addGrade(int standard, String section) throws StudentException;

    /**
     * <p>
     * Finds students by the specified grade ID and returns them as a list of ResponseGradeDto.
     * </p>
     * @param gradeId the UUID of the grade
     * @return a list of ResponseGradeDto representing the students in the specified grade
     * @throws StudentException if there is an error finding the students
     */
    List<ResponseGradeDto> findStudentsByGradeId(String gradeId) throws StudentException;
}
