package com.i2i.cms.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.cms.controller.GradeController;
import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateFeeDto;
import com.i2i.cms.dto.CreateGradeDto;
import com.i2i.cms.dto.StudentDto;
import com.i2i.cms.model.FeeDetail;
import com.i2i.cms.model.Grade;
import com.i2i.cms.model.Student;
import com.i2i.cms.model.Sport;
import com.i2i.cms.repository.GradeRepository;

/**
 * <p>
 * This class manages operations related to grades.
 * </p>
 */
@Service
public class GradeService {

    /**
     * <p>
     * Adds a new grade with the specified standard and section.
     * </p>
     * @param standard The standard of the grade (1 to 12).
     * @param section  The section of the grade (A, B, or C).
     * @return The Grade object that was added or retrieved from the database.
     * @throws StudentException If an error occurs while adding or retrieving the grade.
     */
    @Autowired
    private GradeRepository gradeRepository;
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
    public Grade addGrade(int standard, String section) throws StudentException {
        try {
            logger.debug("Adding grade with standard {} and section {}", standard, section);
            Grade grade = gradeRepository.findGradeByStandardAndSection(standard, section);
            if (null == grade) {
                grade = new Grade();
                grade.setStandard(standard);
                grade.setSection(section);
                grade = gradeRepository.save(grade);
                logger.debug("Grade created with standard {} and section {}", standard, section);
            } else {
                logger.warn("Grade with standard {} and section {} already exists", standard, section);
            }
            return grade;
        } catch (Exception e) {
            logger.error("Error adding grade with standard {} and section {}", standard, section, e);
            throw new StudentException("Error adding grade with standard " + standard + " and section " + section, e);
        }
    }

    /**
     * <p>
     * Retrieves a list of StudentDto objects belonging to a specific grade identified by gradeId.
     * </p>
     * @param gradeId The ID of the grade for which students are to be retrieved.
     * @return A list of StudentDto objects representing students in the grade.
     * @throws StudentException If an error occurs while retrieving students for the grade.
     */
    public List<StudentDto> findStudentsByGradeId(int gradeId) throws StudentException {
        try {
            logger.debug("Finding students for grade ID {}", gradeId);
            Grade grade = gradeRepository.findById(gradeId);
            if (null == grade) {
                logger.warn("Grade ID {} not found", gradeId);
                return null;
            }
            Set<Student> students = grade.getStudents();
            logger.debug("Found students for grade ID {}", gradeId);
            return students.stream()
                    .map(this::mapToStudentDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving students for grade ID {}", gradeId, e);
            throw new StudentException("Error retrieving students for grade ID " + gradeId, e);
        }
    }

    /**
     * <p>
     * Maps a Student object to a StudentDto object.
     * </p>
     * @param student The Student object to be mapped.
     * @return A StudentDto object containing mapped attributes from the Student object.
     */
    private StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setDob(student.getDob());
        Grade grade = student.getGrade();
        if (null == grade) {
            CreateGradeDto gradeDto = new CreateGradeDto();
            gradeDto.setStandard(grade.getStandard());
            gradeDto.setSection(grade.getSection());
            studentDto.setGrade(gradeDto);
        }
        FeeDetail feeDetail = student.getFeeDetail();
        if (null != feeDetail) {
            CreateFeeDto feeDto = new CreateFeeDto();
            feeDto.setTuitionFee(feeDetail.getTuitionFee());
            feeDto.setHostelFee(feeDetail.getHostelFee());
            feeDto.setBusFee(feeDetail.getBusFee());
            studentDto.setFee(feeDto);
        }
        Set<String> selectedSports = student.getSports().stream()
                .map(Sport::getSportName)
                .collect(Collectors.toSet());
        studentDto.setSelectedSports(selectedSports);
        return studentDto;
    }
}


