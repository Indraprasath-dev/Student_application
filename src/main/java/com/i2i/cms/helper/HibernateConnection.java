package com.i2i.cms.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <p>
 * This class manages the Hibernate SessionFactory, providing access to the database
 * for Hibernate-based operations throughout the application.
 * </p>
 */
public class HibernateConnection {
    private static HibernateConnection instance;
    private SessionFactory sessionFactory;
	
    /**
     * <p>
     * Private constructor to prevent instantiation from other classes.
     * </p>
     */
    private HibernateConnection() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
    }
	
    /**
     * <p>
     * Retrieves the singleton instance of HibernateConnection.
     * </p>
     * @return the singleton instance
     */
    public static synchronized HibernateConnection getInstance() {
        if (null == instance) {
            instance = new HibernateConnection();
        }
        return instance;
    }
	
    /**
     * <p>
     * Retrieves the Hibernate SessionFactory instance.
     * </p>
     * @return SessionFactory instance.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	
    /**
     * <p>
     * Closes the Hibernate SessionFactory, releasing all resources.
     * It should be called during application shutdown to clean up database connections.
     * </p>
     */
    public static void shutDown() {
        getInstance().getSessionFactory().close();
    }
}
