package org.academiadecodigo.loginSceneBuilder.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateSessionManager {

    private /*static*/ SessionFactory sessionFactory;

/*    private HibernateSessionManager() {
    }*/

    public HibernateSessionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

/*    static {
        // hibernate initialization code
        try {

            // Hold services needed by Hibernate
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate/hibernate.cfg.xml") // Load settings from hibernate.cfg.xml
                    .build();

            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata() // Tell Hibernate about sources of metadata (database mappings)
                    .buildSessionFactory();

        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError("Error creating hibernate session factory: " + ex.getMessage());
        }
    }*/

    public /*static*/ Session beginTransaction() {
        Session s = getSession();
        s.beginTransaction();
        return s;
    }

    public /*static*/ void commitTransaction() {
        getSession().getTransaction().commit();
    }

    public /*static*/ void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    public /*static*/ Session getSession() {
        // Hibernate will automatically open a new session if needed
        // Closing the session is not required
        return sessionFactory.getCurrentSession();
    }

    // Required to stop hibernate and allow the application to terminate
    public /*static*/ void close() {
        sessionFactory.close();
    }

}

