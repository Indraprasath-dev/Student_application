package com.i2i.cms.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.model.Sport;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class handles data access operations related to sports enrollments for students.
 * It specifically focuses on performing insertion operations on the association table that 
 * links students to their respective sports activities.
 * </p>
 */
@Repository
public class SportDao {
    private static final Logger logger = LoggerFactory.getLogger(SportDao.class);
    private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
	
    /**
     * <p>
     * Inserts a new sport detail into the database.
     * </p>
     * @param sport The Sport object containing the sport details to be inserted.
     * @throws StudentException If there is any error during the insertion process.
     */
    public Sport insertSportDetail(Sport sport) throws StudentException {
        Transaction transaction = null;
        logger.debug("Attempting to insert sport detail: {}", sport);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(sport);
            transaction.commit();
            logger.debug("Successfully inserted sport detail: {}", sport);
	        return sport;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            logger.error("Error occurred while adding sport", e);
            throw new StudentException("Error occurred while adding sport " + sport.getSportId(), e);
        }
    }
	
    /**
     * <p>
     * Retrieves a set of Sport objects based on a list of sport preferences.
     * </p>
     * @param sportPreference A list of sport IDs representing the preferred sports.
     * @return A set of Sport objects matching the preferred sport IDs.
     * @throws StudentException If there is any error during the retrieval process.
     */
    public Set<Sport> retrieveSports(List<Integer> sportPreference) throws StudentException {
        Set<Sport> sports = new HashSet<>();
        logger.debug("Attempting to retrieve sports with preferences: {}", sportPreference);
        try (Session session = sessionFactory.openSession()) {
            if (null != sportPreference) {
                Query<Sport> query = session.createQuery(
                    "SELECT s FROM Sport s WHERE s.sportId IN :preferenceList", Sport.class);
                query.setParameter("preferenceList", sportPreference);
                sports.addAll(query.getResultList());
                logger.debug("Successfully retrieved sports: {}", sports);
            }
            return sports;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving sports ", e);
            throw new StudentException("Error occurred while retrieving sport", e);
        }
    }
}
