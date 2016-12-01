package org.academiadecodigo.loginSceneBuilder.persistence.hibernate;

import org.academiadecodigo.loginSceneBuilder.persistence.TransactionManager;
import org.academiadecodigo.loginSceneBuilder.persistence.hibernate.HibernateSessionManager;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateTransactionManager implements TransactionManager {

    @Override
    public void beginTransaction() {
        HibernateSessionManager.beginTransaction();

    }

    @Override
    public void commit() {
        HibernateSessionManager.commitTransaction();
    }

    @Override
    public void rollback() {
        HibernateSessionManager.rollbackTransaction();
    }

    @Override
    public void close() {
        HibernateSessionManager.close();
    }
}
