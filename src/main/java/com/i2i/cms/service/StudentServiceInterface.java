package com.i2i.cms.service;

import java.util.List;
import java.util.UUID;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateStudentDto;
import com.i2i.cms.dto.StudentInfoDto;
import com.i2i.cms.model.Student;

/**
 * <p>
 * Interface for Student Service operations.
 * Provides methods to add, fetch, delete, find students, and map students to StudentInfoDto.
 * </p>
 */
public interface StudentServiceInterface {

    /**
     * <p>
     * Adds a new student using the specified CreateStudentDto.
     * </p>
     * @param createStudentDto the DTO containing student details
     * @return the added StudentInfoDto object
     * @throws StudentException if there is an error adding the student
     */
    StudentInfoDto addStudent(CreateStudentDto createStudentDto) throws StudentException;

    /**
     * <p>
     * Fetches all students and returns them as a list of StudentInfoDto.
     * </p>
     * @return a list of StudentInfoDto representing all students
     * @throws StudentException if there is an error fetching the students
     */
    List<StudentInfoDto> fetchAllStudents() throws StudentException;

    /**
     * <p>
     * Deletes a student based on the specified student ID.
     * </p>
     * @param studentId the UUID of the student to delete
     * @return true if the deletion is successful, false otherwise
     * @throws StudentException if there is an error deleting the student
     */
    boolean deleteStudentById(UUID studentId) throws StudentException;

    /**
     * <p>
     * Finds a student by the specified student ID and returns the corresponding StudentInfoDto.
     * </p>
     * @param studentId the UUID of the student to find
     * @return the StudentInfoDto representing the found student
     * @throws StudentException if there is an error finding the student
     */
    StudentInfoDto findStudentById(UUID studentId) throws StudentException;

    /**
     * <p>
     * Maps a Student object to a StudentInfoDto.
     * </p>
     * @param student the Student object to map
     * @return the mapped StudentInfoDto
     */
    StudentInfoDto mapToStudentInfoDto(Student student);
}
