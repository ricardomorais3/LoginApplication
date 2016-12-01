package org.academiadecodigo.loginSceneBuilder.model.dao;

import org.academiadecodigo.loginSceneBuilder.model.User;
import org.hibernate.Session;

/**
 * Created by codecadet on 01/12/16.
 */
public interface UserDao {

    void addUser(User user);

    User findByName(String username);

    int count();

}
