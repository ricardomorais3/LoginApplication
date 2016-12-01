package org.academiadecodigo.loginSceneBuilder.service.user;

import org.academiadecodigo.loginSceneBuilder.service.Service;
import org.academiadecodigo.loginSceneBuilder.model.User;

/**
 * Created by codecadet on 17/11/16.
 */
public interface UserService extends Service{

    boolean authenticate(String username, String password);

    void addUser(User user);

    User findByName(String username);

    int count();

}
