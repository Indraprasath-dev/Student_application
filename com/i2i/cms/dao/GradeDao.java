package com.i2i.cms.dao;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.model.Grade;

/**
 * <p>
 * This class handles data access operations related to managing standards, sections, 
 * and the students associated with them in the database.
 * It provides methods to add new grade details, check for the existence of specific 
 * standard and section combinations, and retrieve grades along with their associated students.
 * </p>
 */
public class GradeDao {
    private HibernateConnection hibernateConnection = HibernateConnection.getInstance();
    private SessionFactory sessionFactory = hibernateConnection.getSessionFactory();
    
    /**
     * <p>
     * Adds grade details to the database.
     * </p>
     * @param grade The Grade object containing the details to be added.
     * @throws StudentException If an error occurs while adding the grade to the database.
     */
    public void addGradeDetail(Grade grade) throws StudentException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(grade);
            transaction.commit();
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Error occurred while adding grade: " + grade.getStandard(), e);
        } 
    }
    
    /**
     * <p>
     * Retrieves a Grade object by checking if the specified standard and section combination 
     * is present in the database.
     * </p>
     * @param standard The standard to check.
     * @param section The section to check.
     * @return The Grade object if the specified section and standard are available, otherwise null.
     * @throws StudentException If an error occurs while checking grade availability.
     */
    public Grade findGradeByStandardAndSection(int standard, String section) throws StudentException {
        Grade grade = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Grade> query = session.createQuery("FROM Grade WHERE standard = :standard AND section = :section", Grade.class);
            query.setParameter("standard", standard);
            query.setParameter("section", section);
            grade = query.uniqueResult();
            return grade; 
        } catch (Exception e) {
            throw new StudentException("Unable to retrieve grade detail for standard " + standard + " and section " + section, e);
        } 	
    }

    /**
     * <p>
     * Retrieves a Grade object along with the list of students associated with the specified grade ID.
     * </p>
     * @param gradeId The ID of the grade to retrieve.
     * @return The Grade object containing the list of associated students.
     * @throws StudentException If an error occurs while retrieving the grade and associated students.
     */
    public Grade retrieveStudentsByGradeId(int gradeId) throws StudentException {
        Grade grade = null;
        try (Session session = sessionFactory.openSession()) {
            grade = session.get(Grade.class, gradeId);
            return grade;
        } catch (Exception e) {
            throw new StudentException("Unable to retrieve grade with ID: " + gradeId, e);
        }
    }
}
