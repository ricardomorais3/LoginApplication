package org.academiadecodigo.loginSceneBuilder.model.dao.hibernate;

import org.academiadecodigo.loginSceneBuilder.model.User;
import org.academiadecodigo.loginSceneBuilder.model.dao.UserDao;
import org.academiadecodigo.loginSceneBuilder.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginSceneBuilder.persistence.TransactionException;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateUserDao implements UserDao {

    @Override
    public void addUser(User user) {
        try {
            HibernateSessionManager.getSession().save(user);
        } catch (HibernateException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public User findByName(String username) {
        User tempUser = null;
        try {
            tempUser = (User) HibernateSessionManager.getSession().createCriteria(User.class)
                    .add(Restrictions.eq("username", username)).uniqueResult();
        } catch (HibernateException e) {
            throw new TransactionException(e);
        }
        return tempUser;
    }

    @Override
    public int count() {
        int count = 0;
        try {
            count = HibernateSessionManager.getSession().createCriteria(User.class).list().size();
        } catch (HibernateException e) {
            throw new TransactionException(e);
        }
        return count;
    }
}
