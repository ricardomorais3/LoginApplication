package org.academiadecodigo.loginSceneBuilder.service.user;

import org.academiadecodigo.loginSceneBuilder.model.User;
import org.academiadecodigo.loginSceneBuilder.model.dao.RoleDao;
import org.academiadecodigo.loginSceneBuilder.model.dao.UserDao;
import org.academiadecodigo.loginSceneBuilder.persistence.TransactionException;
import org.academiadecodigo.loginSceneBuilder.persistence.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by codecadet on 01/12/16.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roledao;
    /*private TransactionManager transactionManager;*/

    public UserServiceImpl(UserDao userDao, RoleDao roleDao/*, TransactionManager transactionManager*/) {
        this.userDao = userDao;
        this.roledao = roleDao;
        /*this.transactionManager = transactionManager;*/
    }

    @Transactional
    @Override
    public boolean authenticate(String username, String password) {

        boolean result = false;

/*        try {

            transactionManager.beginTransaction();*/

        User user = userDao.findByName(username);
        result = (user != null && user.getPassword().equals(password));

/*            transactionManager.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            transactionManager.rollback();

        }*/

        return result;

    }

    /**
     * Adds a new user
     *
     * @param user the new user to add
     */
    @Transactional
    @Override
    public void addUser(User user) {

/*        try {

            transactionManager.beginTransaction();*/

        if (userDao.findByName(user.getUsername()) == null) {
            userDao.add(user);
        }

/*            transactionManager.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            transactionManager.rollback();

        }*/

    }

/*    @Override
    public void addUserRole(String username, String role) {

        try {

            tx.begin();

            User user = userDao.findByName(username);
            Role r = roleDao.findByName(role);

            if (user != null && r != null) {
                user.getRoles().add(r);
                userDao.save(user);
            }

            tx.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            tx.rollback();

        }

    }*/

    /**
     * Finds a user by name
     *
     * @param username the user name used to lookup a user
     * @return a new User if found, null otherwise
     */
    @Transactional
    @Override
    public User findByName(String username) {

        User user = null;

/*        try {

            transactionManager.beginTransaction();*/

        user = userDao.findByName(username);

/*        transactionManager.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            transactionManager.rollback();

        }*/

        return user;

    }

    /**
     * Count the number of existing users
     *
     * @return the number of users
     */
    @Transactional
    @Override
    public int count() {

        int size = 0;

/*        try {

            transactionManager.beginTransaction();*/

        size = userDao.count();

/*            transactionManager.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            transactionManager.rollback();

        }*/

        return size;

    }

/*    @Override
    public boolean authenticate(String username, String password) {
        User tempUser;
        return ((tempUser = findByName(username)) != null) && tempUser.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {

        try {
            transactionManager.beginTransaction();

            userDao.add(user);
            transactionManager.commit();
        } catch (TransactionException e) {
            transactionManager.rollback();
        }
    }

    @Override
    public User findByName(String username) {
        User tempUser = null;
        try {
            transactionManager.beginTransaction();
            tempUser = userDao.findByName(username);
            transactionManager.commit();
        } catch (TransactionException e) {
            transactionManager.rollback();
        }
        return tempUser;
    }

    @Override
    public int count() {
        int count = -1;
        try {
            transactionManager.beginTransaction();
            count = userDao.count();
            transactionManager.commit();
        } catch (TransactionException e) {
            transactionManager.rollback();
        }
        return count;
    }*/

    @Override
    public String getName() {
        return UserService.class.getName();
    }
}
