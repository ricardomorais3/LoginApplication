package org.academiadecodigo.loginSceneBuilder.model;

import org.academiadecodigo.loginSceneBuilder.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginSceneBuilder.service.user.UserService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by codecadet on 01/12/16.
 */
public class HibernateUserService implements UserService {

    //private String serviceName = "HibernateUserService";

    @Override
    public boolean authenticate(String username, String password) {

        User tempUser;
        return ((tempUser = findByName(username)) != null) && tempUser.getPassword().equals(password);

        //todo: use queries to get the boolean
/*        try{
            Session session = HibernateSessionManager.beginTransaction();

        }catch (HibernateException e){
            HibernateSessionManager.rollbackTransaction();
        }*/
    }

    @Override
    public void addUser(User user) {

        try {
            Session session = HibernateSessionManager.beginTransaction();
            session.save(user);
            HibernateSessionManager.commitTransaction();

        }catch (HibernateException e){
            HibernateSessionManager.rollbackTransaction();
        }
    }

    @Override
    public User findByName(String username) {
        User tempUser = null;

        try {

            Session session = HibernateSessionManager.beginTransaction();
            tempUser = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("username", username)).uniqueResult();

            HibernateSessionManager.commitTransaction();

        } catch (HibernateException e) {
            HibernateSessionManager.rollbackTransaction();
        }
        return tempUser;
    }

    @Override
    public int count() {

        int count = 0;

        try {
            Session session = HibernateSessionManager.beginTransaction();

            count = session.createCriteria(User.class).list().size();

            HibernateSessionManager.commitTransaction();

        } catch (HibernateException e) {
            HibernateSessionManager.rollbackTransaction();
        }

        return count;
    }

    @Override
    public String getName() {
        return UserService.class.getName();
    }
}
