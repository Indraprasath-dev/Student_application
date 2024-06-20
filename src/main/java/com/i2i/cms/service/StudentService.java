package com.i2i.cms.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    public Student addStudentWithFeeDetail(String name, Date dob, int tuitionFee, int busFee
                            , int hostelFee , int standard, String section, List<Integer> selectedSports) throws StudentException {
        Student student = new Student();
        Grade grade = gradeService.addGradeId(standard, section);
        student.setGrade(grade);
        student.setName(name);
        student.setDob(dob);
        FeeDetail feeDetail = new FeeDetail();
        feeDetail.setTuitionFee(tuitionFee);
        feeDetail.setBusFee(busFee);
        feeDetail.setHostelFee(hostelFee);
        Set<Sport> sports = sportService.retrieveSports(selectedSports);
        student.setSports(sports);
        student.setFeeDetail(feeDetail);
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
        Student student = studentDao.findStudentById(studentId);
        if (null == student) {
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
        return studentDao.deleteStudentById(studentId);
    }
}
