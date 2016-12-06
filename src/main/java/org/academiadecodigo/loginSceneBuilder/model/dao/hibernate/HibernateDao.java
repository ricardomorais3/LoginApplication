package org.academiadecodigo.loginSceneBuilder.model.dao.hibernate;

import org.academiadecodigo.loginSceneBuilder.model.dao.Dao;
import org.academiadecodigo.loginSceneBuilder.persistence.TransactionException;
import org.academiadecodigo.loginSceneBuilder.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;

/**
 * Created by codecadet on 05/12/16.
 */
public abstract class HibernateDao<T> implements Dao<T>{

    private Class<T> type;
    public HibernateSessionManager hibernateSessionManager;

    public HibernateDao(Class<T> type) {
        this.type = type;
    }


    @Override
    public void add(T t) {
        try {

            hibernateSessionManager.getSession().saveOrUpdate(t);

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }
    }

    @Override
    public int count() {
        try {

            return (int) hibernateSessionManager.getSession().createCriteria(type)
                    .setProjection(Projections.rowCount())
                    .uniqueResult();

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }
    }

    @Override
    public void delete(T t) {
        try {

            hibernateSessionManager.getSession().delete(t);

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }
    }

    public void setHibernateSessionManager(HibernateSessionManager hibernateSessionManager) {
        this.hibernateSessionManager = hibernateSessionManager;
    }
}
