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
import com.i2i.cms.dto.CreateGradeDto;
import com.i2i.cms.dto.CreateStudentDto;
import com.i2i.cms.dto.StudentInfoDto;
import com.i2i.cms.dto.UpdateStudentDto;
import com.i2i.cms.model.FeeDetail;
import com.i2i.cms.model.Grade;
import com.i2i.cms.model.Sport;
import com.i2i.cms.model.Student;
import com.i2i.cms.repository.StudentRepository;
import com.i2i.cms.util.DateUtil;

/**
 * <p>
 * Service class providing operations related to students.
 * </p>
 */
@Service
public class StudentService implements StudentServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SportService sportService;

    /**
     * <p>
     * Adds a new student based on the details from the provided CreateStudentDto object.
     * </p>
     * @param createStudentDto The CreateStudentDto object containing details of the student to be added.
     * @return The StudentInfoDto object representing the added student.
     * @throws StudentException If an error occurs while adding the student.
     */
    public StudentInfoDto addStudent(CreateStudentDto createStudentDto) throws StudentException {
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
            feeDetail.setStudent(student);
            student.setFeeDetail(feeDetail);
            Set<Sport> sports = sportService.retrieveSports(createStudentDto.getSelectedSports());
            student.setSports(sports);
            Student savedStudent = studentRepository.save(student);
            logger.debug("Student added successfully with ID: {}", savedStudent.getId());
            return mapToStudentInfoDto(savedStudent);
        } catch (Exception e) {
            logger.error("Error adding student: {}", createStudentDto.getName(), e);
            throw new StudentException("Error adding student " + createStudentDto.getName(), e);
        }
    }

    /**
     * <p>
     * Fetches all students from the repository.
     * </p>
     * @return A list of StudentInfoDto objects representing all students.
     * @throws StudentException If an error occurs while fetching students.
     */
    public List<StudentInfoDto> fetchAllStudents() throws StudentException {
        try {
            logger.debug("Fetching all students");
            List<Student> students = studentRepository.findAll();
            logger.debug("Fetched {} students", students.size());
            return students.stream()
                    .map(this::mapToStudentInfoDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching all students", e);
            throw new StudentException("Error fetching all students", e);
        }
    }

    /**
     * <p>
     * Deletes a student based on the provided student ID.
     * </p>
     * @param studentId The ID of the student to be deleted.
     * @return True if the student was successfully deleted, false otherwise.
     * @throws StudentException If an error occurs while deleting the student.
     */
     public boolean deleteStudentById(UUID studentId) throws StudentException {
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
     * Finds a student by their ID.
     * </p>
     * @param studentId The ID of the student to find.
     * @return The StudentInfoDto object representing the found student, or null if not found.
     * @throws StudentException If an error occurs while finding the student.
     */
    public StudentInfoDto findStudentById(UUID studentId) throws StudentException {
        try {
            logger.debug("Finding student with ID: {}", studentId);
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            if (!studentOptional.isPresent()) {
                logger.warn("Student with ID {} not found", studentId);
                return null;
            }
            return mapToStudentInfoDto(studentOptional.get());
        } catch (Exception e) {
            logger.error("Error finding student with ID: {}", studentId, e);
            throw new StudentException("Error finding student with ID " + studentId, e);
        }
    }

    /**
     * <p>
     * Updates a student's details based on the provided UpdateStudentDto object.
     * </p>
     * @param updateStudentDto The UpdateStudentDto object containing updated details of the student.
     * @return The StudentInfoDto object representing the updated student.
     * @throws StudentException If an error occurs while updating the student.
     */
    public StudentInfoDto updateStudentById(UpdateStudentDto updateStudentDto) throws StudentException {
        try {
            Optional<Student> studentOptional = studentRepository.findById(updateStudentDto.getId());
            if (!studentOptional.isPresent()) {
                logger.warn("Student with ID {} not found", updateStudentDto.getId());
                return null;
            }
            Student student = studentOptional.get();
            student.setName(updateStudentDto.getName());
            student.setDob(updateStudentDto.getDob());
            Student savedStudent = studentRepository.save(student);
            return mapToStudentInfoDto(savedStudent);
        } catch (Exception e) {
            logger.error("Error updating student with ID: {}", updateStudentDto.getId(), e);
            throw new StudentException("Error updating student with ID " + updateStudentDto.getId(), e);
        }
    }

    /**
     * <p>
     * Maps a Student entity to a StudentInfoDto object.
     * </p>
     * @param student The Student entity to be mapped.
     * @return The StudentInfoDto object containing mapped attributes from the Student entity.
     */
    public StudentInfoDto mapToStudentInfoDto(Student student) {
        StudentInfoDto studentInfoDto = new StudentInfoDto();
        studentInfoDto.setId(student.getId());
        studentInfoDto.setName(student.getName());
        studentInfoDto.setDob(student.getDob());
        studentInfoDto.setAge(DateUtil.calculateDifferenceOfDates(student.getDob()));
        CreateGradeDto gradeDto = new CreateGradeDto();
        gradeDto.setGradeId(student.getGrade().getGradeId());
        gradeDto.setStandard(student.getGrade().getStandard());
        gradeDto.setSection(student.getGrade().getSection());
        studentInfoDto.setGrade(gradeDto);
        CreateFeeDto feeDto = new CreateFeeDto();
        feeDto.setTuitionFee(student.getFeeDetail().getTuitionFee());
        feeDto.setHostelFee(student.getFeeDetail().getHostelFee());
        feeDto.setBusFee(student.getFeeDetail().getBusFee());
        studentInfoDto.setFee(feeDto);
        Set<String> selectedSports = student.getSports().stream()
                .map(Sport::getSportName)
                .collect(Collectors.toSet());
        studentInfoDto.setSelectedSports(selectedSports);
        return studentInfoDto;
    }
}
