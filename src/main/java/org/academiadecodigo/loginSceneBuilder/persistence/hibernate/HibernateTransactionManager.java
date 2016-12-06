package org.academiadecodigo.loginSceneBuilder.persistence.hibernate;

import org.academiadecodigo.loginSceneBuilder.persistence.TransactionManager;
import org.academiadecodigo.loginSceneBuilder.persistence.hibernate.HibernateSessionManager;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateTransactionManager implements TransactionManager {

    private HibernateSessionManager hibernateSessionManager;

    @Override
    public void beginTransaction() {
        hibernateSessionManager.beginTransaction();
    }

    @Override
    public void commit() {
        hibernateSessionManager.commitTransaction();
    }

    @Override
    public void rollback() {
        hibernateSessionManager.rollbackTransaction();
    }

    @Override
    public void close() {
        hibernateSessionManager.close();
    }

    public void setHibernateSessionManager(HibernateSessionManager hibernateSessionManager) {
        this.hibernateSessionManager = hibernateSessionManager;
    }
}
