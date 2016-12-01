package org.academiadecodigo.loginSceneBuilder.service.user;

import org.academiadecodigo.loginSceneBuilder.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codecadet on 17/11/16.
 */
public class MockUserService implements UserService {

    //private String serviceName = "MockUserService";
    private List<User> userList;

    public MockUserService(){
        userList = new ArrayList<>();
    }

    @Override
    public boolean authenticate(String username, String password) {
        User tempUser;
        return ((tempUser = findByName(username)) != null) && tempUser.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
        for (User myUser: userList) {
            System.out.println(myUser);
        }
    }

    @Override
    public User findByName(String username) {
        for (User user: userList) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    @Override
    public int count() {
        return userList.size();
    }

    @Override
    public String getName() {
        return UserService.class.getName();
    }
}
