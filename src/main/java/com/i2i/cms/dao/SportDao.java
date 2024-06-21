package com.i2i.cms.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.model.Sport;

/**
 * <p>
 * This class handles data access operations related to sports enrollments for students.
 * It specifically focuses on performing insertion operations on the association table that 
 * links students to their respective sports activities.
 * </p>
 */
public class SportDao {
    private HibernateConnection hibernateConnection = HibernateConnection.getInstance();
    private SessionFactory sessionFactory = hibernateConnection.getSessionFactory();
	
    /**
     * <p>
     * Inserts a new sport detail into the database.
     * </p>
     * @param sport The Sport object containing the sport details to be inserted.
     * @throws StudentException If there is any error during the insertion process.
     */
    public Sport insertSportDetail(Sport sport) throws StudentException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(sport);
            transaction.commit();
	    return sport;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
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
        try (Session session = sessionFactory.openSession()) {
            if (null != sportPreference) {
                Query<Sport> query = session.createQuery(
                    "SELECT s FROM Sport s WHERE s.sportId IN :preferenceList", Sport.class);
                query.setParameter("preferenceList", sportPreference);
                sports.addAll(query.getResultList());
            }
            return sports;
        } catch (Exception e) {
            throw new StudentException("Error occurred while retrieving sport", e);
        }
    }
}
