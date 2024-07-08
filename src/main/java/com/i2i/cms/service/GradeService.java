package com.i2i.cms.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateFeeDto;
import com.i2i.cms.dto.ResponseGradeDto;
import com.i2i.cms.model.FeeDetail;
import com.i2i.cms.model.Grade;
import com.i2i.cms.model.Sport;
import com.i2i.cms.model.Student;
import com.i2i.cms.repository.GradeRepository;

/**
 * <p>
 * Service class for handling operations related to grades and students.
 * </p>
 */
@Service
public class GradeService {
    private static final Logger logger = LoggerFactory.getLogger(GradeService.class);

    @Autowired
    private GradeRepository gradeRepository;

    /**
     * <p>
     * Adds a new grade with the specified standard and section.
     * </p>
     * @param standard The standard of the grade (1 to 12).
     * @param section  The section of the grade (A, B, or C).
     * @return the added Grade object
     * @throws StudentException if an error occurs while adding the grade
     */
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
     * Finds students associated with the given grade ID and returns their details as a list of ResponseGradeDto.
     * </p>
     * @param gradeId the UUID of the grade to search for
     * @return a list of ResponseGradeDto objects representing students in the grade
     * @throws StudentException if an error occurs while retrieving students
     */
    public List<ResponseGradeDto> findStudentsByGradeId(UUID gradeId) throws StudentException {
        try {
            logger.debug("Finding students for grade ID {}", gradeId);
            Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);
            if (!gradeOptional.isPresent()) {
                logger.warn("Grade ID {} not found", gradeId);
                throw new StudentException("Grade ID not found: " + gradeId);
            }
            Grade grade = gradeOptional.get();
            Set<Student> students = grade.getStudents();
            logger.debug("Found {} students for grade ID {}", students.size(), gradeId);
            return students.stream()
                    .map(this::mapToResponseGradeDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving students for grade ID {}", gradeId, e);
            throw new StudentException("Error retrieving students for grade ID " + gradeId, e);
        }
    }

    /**
     * <p>
     * Maps a Student entity to a ResponseGradeDto object.
     * </p>
     * @param student the Student entity to map
     * @return the mapped ResponseGradeDto object
     */
    private ResponseGradeDto mapToResponseGradeDto(Student student) {
        ResponseGradeDto responseGradeDto = new ResponseGradeDto();
        responseGradeDto.setId(student.getId()); // Assuming ID is UUID
        responseGradeDto.setName(student.getName());
        responseGradeDto.setDob(student.getDob());
        FeeDetail feeDetail = student.getFeeDetail();
        if (null != feeDetail) {
            CreateFeeDto feeDto = new CreateFeeDto();
            feeDto.setTuitionFee(feeDetail.getTuitionFee());
            feeDto.setHostelFee(feeDetail.getHostelFee());
            feeDto.setBusFee(feeDetail.getBusFee());
            responseGradeDto.setFee(feeDto);
        }
        Set<String> selectedSports = student.getSports().stream()
                .map(Sport::getSportName)
                .collect(Collectors.toSet());
        responseGradeDto.setSelectedSports(selectedSports);
        return responseGradeDto;
    }
}
