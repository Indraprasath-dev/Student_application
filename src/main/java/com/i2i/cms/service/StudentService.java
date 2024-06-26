package com.i2i.cms.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dao.StudentDao;
import com.i2i.cms.model.Grade;
import com.i2i.cms.model.Sport;
import com.i2i.cms.model.Student;
import com.i2i.cms.model.FeeDetail;
import com.i2i.cms.service.GradeService;

/**
 * <p>
 * This class is for managing student records.
 * It has the ability to search for students, retrieve all student records,
 * add new students and remove students.
 * </p>
 */
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private StudentDao studentDao = new StudentDao();
    private GradeService gradeService = new GradeService();
    private SportService sportService = new SportService();
    
    /**
     * <p>
     * Adds a new student record.
     * </p>
     * @param name
     *        The name of the student.
     * @param dob
     *        The date of birth of the student in the format YYYY-MM-DD.
     * @param tuitionFee
     *        The tuition fee for the student.
     * @param busFee
     *        The bus fee for the student.
     * @param hostelFee
     *        The hostel fee for the student.
     * @param standard
     *        The class of the student, should be in the range of 1 to 12.
     * @param section
     *        The section of the student, should be A, B or C.
     * @param selectedSports
     *        The list of selected sport IDs.
     * @return The newly created Student object.
     * @throws StudentException
     *         If an error occurs while adding the student.
     */
    public Student addStudent(String name, Date dob, int tuitionFee, int busFee
                            , int hostelFee , int standard, String section, List<Integer> selectedSports) throws StudentException {
        logger.info("Adding new student detail");
        Student student = new Student();
        student.setName(name);
        student.setDob(dob);
        Grade grade = gradeService.addGradeId(standard, section);
        student.setGrade(grade);
        FeeDetail feeDetail = new FeeDetail();
        feeDetail.setTuitionFee(tuitionFee);
        feeDetail.setBusFee(busFee);
        feeDetail.setHostelFee(hostelFee);
        feeDetail.setStudent(student);
        student.setFeeDetail(feeDetail);
        Set<Sport> sports = sportService.retrieveSports(selectedSports);
        student.setSports(sports);
        return studentDao.insertStudent(student);
    }
    
    /**
     * <p>
     * Retrieves all student records.
     * </p>
     * @return A list of all students.
     * @throws StudentException
     *         If an error occurs while retrieving the students.
     */
    public List<Student> getAllStudents() throws StudentException {
        logger.info("Retrieving all students.");
        return studentDao.getAllStudents();
    }
    
    /**
     * <p>
     * Finds a student by their ID.
     * </p>
     * @param studentId
     *        The ID of the student to be found.
     * @return The student with the specified ID, or null if no such student exists.
     * @throws StudentException
     *         If an error occurs while finding the student.
     */
    public Student findStudentById(int studentId) throws StudentException {
        logger.debug("Finding student with ID: {}", studentId);
        return studentDao.findStudentById(studentId);
    }
    
    /**
     * <p>
     * Updates the name of a student by their ID.
     * </p>
     * @param studentId
     *        The ID of the student.
     * @param newName
     *        The new name of the student.
     * @return The updated student object, or null if no student with the specified ID exists.
     * @throws StudentException
     *         If an error occurs while updating the student's name.
     */
    public Student updateStudentNameById(int studentId, String newName) throws StudentException {
        logger.debug("Updating student name: studentId={}, newName={}", studentId, newName);
        Student student = studentDao.findStudentById(studentId);
        if (null == student) {
            logger.warn("Student with ID {} not found.", studentId);
            return null;
        }
        student.setName(newName);
        return studentDao.updateStudentNameById(student);
    }
    
    /**
     * <p>
     * Removes a student identified by their ID.
     * </p>
     * @param studentId
     *        The ID of the student to be removed.
     * @return True if the removal was successful, false otherwise.
     * @throws StudentException
     *         If an error occurs while removing the student.
     */
    public boolean removeStudent(int studentId) throws StudentException {
        logger.debug("Removing student with ID: {}", studentId);
        return studentDao.deleteStudentById(studentId);
    }
}
