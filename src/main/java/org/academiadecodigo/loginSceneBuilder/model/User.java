package org.academiadecodigo.loginSceneBuilder.model;

/**
 * Created by codecadet on 17/11/16.
 */
public class User {

    private String username;
    private String password;
    private String email;
    private int id;
    private Role role;

    public User() {
    }

    public User(String username, String password, String email) {
        this.password = password;
        this.email = email;
        this.username = username;
        role = new Role(2,"normal");
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }


    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
