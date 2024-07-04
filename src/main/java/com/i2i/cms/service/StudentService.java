package com.i2i.cms.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.cms.controller.StudentController;
import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateFeeDto;
import com.i2i.cms.dto.CreateGradeDto;
import com.i2i.cms.dto.CreateStudentDto;
import com.i2i.cms.dto.StudentDto;
import com.i2i.cms.model.*;
import com.i2i.cms.repository.StudentRepository;

/**
 * <p>
 * This class is for managing student records.
 * It has the ability to search for students, retrieve all student records,
 * add new students and remove students.
 * </p>
 */
@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SportService sportService;

    /**
     * <p>
     * Adds a new student based on the provided CreateStudentDto object.
     * </p>
     * @param createStudentDto The CreateStudentDto object containing student details.
     * @return The StudentDto object representing the added student.
     * @throws StudentException If an error occurs while adding the student.
     */
    public StudentDto addStudent(CreateStudentDto createStudentDto) throws StudentException {
        try {
            logger.debug("Adding student: {}", createStudentDto.getName());
            Student student = new Student();
            student.setName(createStudentDto.getName());
            student.setDob(createStudentDto.getDob());
            CreateGradeDto gradeDto = createStudentDto.getGrade();
            Grade grade = gradeService.addGrade(gradeDto.getStandard(), gradeDto.getSection());
            student.setGrade(grade);
            CreateFeeDto feeDto = createStudentDto.getFee();
            FeeDetail feeDetail = new FeeDetail();
            feeDetail.setTuitionFee(feeDto.getTuitionFee());
            feeDetail.setHostelFee(feeDto.getHostelFee());
            feeDetail.setBusFee(feeDto.getBusFee());
            student.setFeeDetail(feeDetail);
            Set<Sport> sports = sportService.retrieveSports(createStudentDto.getSelectedSports());
            student.setSports(sports);
            Student savedStudent = studentRepository.save(student);
            logger.debug("Student added successfully with ID: {}", savedStudent.getId());
            return mapToStudentDto(savedStudent);
        } catch (Exception e) {
            logger.error("Error adding student: {}", createStudentDto.getName(), e);
            throw new StudentException("Error adding student" + createStudentDto.getName(), e);
        }
    }

    /**
     * <p>
     * Retrieves all students from the database.
     * </p>
     * @return A list of StudentDto objects representing all students.
     * @throws StudentException If an error occurs while fetching students.
     */
    public List<StudentDto> fetchAllStudents() throws StudentException {
        try {
            logger.debug("Fetching all students");
            List<Student> students = studentRepository.findAll();
            logger.debug("Fetched {} students", students.size());
            return students.stream()
                    .map(this::mapToStudentDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching all students", e);
            throw new StudentException("Error fetching all students", e);
        }
    }

    /**
     * <p>
     * Deletes a student from the database based on the provided student ID.
     * </p>
     * @param studentId The ID of the student to delete.
     * @return true if the student was successfully deleted, false otherwise.
     * @throws StudentException If an error occurs while deleting the student.
     */
    public boolean deleteStudentById(int studentId) throws StudentException {
        try {
            logger.debug("Deleting student with ID: {}", studentId);
            if (!studentRepository.existsById(studentId)) {
                logger.warn("Student with ID {} not found for deletion", studentId);
                return false;
            }
            studentRepository.deleteById(studentId);
            logger.info("Student with ID {} deleted successfully", studentId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting student with ID: {}", studentId, e);
            throw new StudentException("Error deleting student with ID " + studentId, e);
        }
    }

    /**
     * <p>
     * Finds a student from the database based on the provided student ID.
     * </p>
     * @param studentId The ID of the student to find.
     * @return The StudentDto object representing the found student, or null if not found.
     * @throws StudentException If an error occurs while finding the student.
     */
    public StudentDto findStudentById(int studentId) throws StudentException {
        try {
            logger.debug("Finding student with ID: {}", studentId);
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            if (!studentOptional.isPresent()) {
                logger.warn("Student with ID {} not found", studentId);
                return null;
            }
            return mapToStudentDto(studentOptional.get());
        } catch (Exception e) {
            logger.error("Error finding student with ID: {}", studentId, e);
            throw new StudentException("Error finding student with ID " + studentId, e);
        }
    }

    /**
     * <p>
     * Maps a Student entity to a StudentDto object.
     * </p>
     * @param student The Student entity to be mapped.
     * @return The StudentDto object containing mapped attributes from the Student entity.
     */
    private StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setDob(student.getDob());
        Grade grade = student.getGrade();
        if (null != grade) {
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
