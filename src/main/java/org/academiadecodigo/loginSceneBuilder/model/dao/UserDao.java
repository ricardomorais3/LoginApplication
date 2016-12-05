package org.academiadecodigo.loginSceneBuilder.model.dao;

import org.academiadecodigo.loginSceneBuilder.model.User;

/**
 * Created by codecadet on 01/12/16.
 */
public interface UserDao {

    void add(User user);

    User findByName(String username);

    int count();

}
