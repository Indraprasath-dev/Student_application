package com.i2i.cms.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.model.Student;

/**
 * <p>
 * This class is responsible for managing student records within the database.
 * It provides methods for inserting new students, retrieving all student records,
 * updating student details, finding students by their ID, and deleting students from the database.
 * </p>
 */
public class StudentDao {
    private HibernateConnection hibernateConnection = HibernateConnection.getInstance();
    private SessionFactory sessionFactory = hibernateConnection.getSessionFactory();
	
    /**
     * <p>
     * Inserts a new student record into the database.
     * </p>
     * @param student The Student object containing the details to be inserted.
     * @return The inserted Student object.
     * @throws StudentException If any error occurs during the insertion.
     */
    public Student insertStudent(Student student) throws StudentException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
			throw new StudentException("Error occurred while adding student " + student.getName(), e);
        }
    }
	
    /**
     * <p>
     * Retrieves all student records from the database.
     * </p>
     * @return A list of all Student objects.
     * @throws StudentException If any error occurs during retrieval.
     */
    public List<Student> getAllStudents() throws StudentException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            throw new StudentException("Unable to get the student details", e);
        }
    }
	
    /**
     * <p>
     * Finds a student by their ID.
     * </p>
     * @param studentId The ID of the student to be found.
     * @return The Student object with the specified ID, or null if no student is found.
     * @throws StudentException If any error occurs during the search.
     */
    public Student findStudentById(int studentId) throws StudentException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, studentId);
        } catch (Exception e) {
            throw new StudentException("Unable to find the student details for ID " + studentId, e);
        } 
    }
	
    /**
     * <p>
     * Updates the name of a student in the database.
     * </p>
     * @param student The student object containing the updated information.
     * @return The updated student object.
     * @throws StudentException If an error occurs while updating the student's name.
     */
    public Student updateStudentNameById(Student student) throws StudentException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
			transaction.commit();
            return student;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to update the student name for ID " + student.getId(), e);
        }
    }

    /**
     * <p>
     * Deletes a student by their ID from the database.
     * </p>
     * @param studentId The ID of the student to be deleted.
     * @return True if the deletion was successful, false otherwise.
     * @throws StudentException If any error occurs during the deletion.
     */
    public boolean deleteStudentById(int studentId) throws StudentException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (null != student) {
                session.delete(student);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to delete the student details for ID " + studentId, e);
        }
    }
}
