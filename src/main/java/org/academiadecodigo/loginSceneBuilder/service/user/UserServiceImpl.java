package org.academiadecodigo.loginSceneBuilder.service.user;

import org.academiadecodigo.loginSceneBuilder.model.User;
import org.academiadecodigo.loginSceneBuilder.model.dao.RoleDao;
import org.academiadecodigo.loginSceneBuilder.model.dao.UserDao;
import org.academiadecodigo.loginSceneBuilder.persistence.TransactionException;
import org.academiadecodigo.loginSceneBuilder.persistence.TransactionManager;

/**
 * Created by codecadet on 01/12/16.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private TransactionManager transactionManager;
    private RoleDao roledao;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
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
    }

    @Override
    public String getName() {
        return UserService.class.getName();
    }
}
