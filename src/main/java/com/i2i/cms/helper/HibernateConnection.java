package com.i2i.cms.helper;

import io.github.cdimascio.dotenv.Dotenv;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <p>
 * This class manages the Hibernate SessionFactory, providing access to the database
 * for Hibernate-based operations throughout the application.
 * </p>
 */
public class HibernateConnection {
    private static SessionFactory sessionFactory;

    static{
            Dotenv dotenv = Dotenv.load();
            Configuration configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.url", dotenv.get("URL"));
            configuration.setProperty("hibernate.connection.username", dotenv.get("USER"));
            configuration.setProperty("hibernate.connection.password", dotenv.get("PASSWORD"));
            sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * <p>
     * Retrieves the Hibernate SessionFactory instance.
     * </p>
     * @return SessionFactory instance.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
