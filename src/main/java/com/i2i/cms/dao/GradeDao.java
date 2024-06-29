package com.i2i.cms.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.model.Grade;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class handles data access operations related to managing standards, sections, 
 * and the students associated with them in the database.
 * It provides methods to add new grade details, check for the existence of specific 
 * standard and section combinations, and retrieve grades along with their associated students.
 * </p>
 */
@Repository
public class GradeDao {
    private static final Logger logger = LoggerFactory.getLogger(GradeDao.class);
    private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    /**
     * <p>
     * Adds grade details to the database.
     * </p>
     * @param grade The Grade object containing the details to be added.
     * @throws StudentException If an error occurs while adding the grade to the database.
     */
    public void addGradeDetail(Grade grade) throws StudentException {
        Transaction transaction = null;
        logger.debug("Attempting to add grade detail: {}", grade);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(grade);
            transaction.commit();
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error occurred while adding grade", e);
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
        logger.debug("Attempting to find grade by standard: {} and section: {}", standard, section);
        try (Session session = sessionFactory.openSession()) {
            Query<Grade> query = session.createQuery("FROM Grade WHERE standard = :standard AND section = :section", Grade.class);
            query.setParameter("standard", standard);
            query.setParameter("section", section);
            grade = query.uniqueResult();
            return grade;
        } catch (Exception e) {
            logger.error("Unable to retrieve grade detail", e);
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
        logger.debug("Attempting to retrieve students by grade ID: {}", gradeId);
        try (Session session = sessionFactory.openSession()) {
            grade = session.get(Grade.class, gradeId);
            logger.debug("Successfully retrieved grade: {}", grade);
            return grade;
        } catch (Exception e) {
            logger.error("Unable to retrieve grade", e);
            throw new StudentException("Unable to retrieve grade with ID: " + gradeId, e);
        }
    }
}
