package org.academiadecodigo.loginSceneBuilder.model.dao;

import org.academiadecodigo.loginSceneBuilder.model.User;

/**
 * Created by codecadet on 01/12/16.
 */
public interface UserDao extends Dao<User>{

/*    void add(User user);

    int count();*/

    User findByName(String username);

}
