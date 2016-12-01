package org.academiadecodigo.loginSceneBuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import org.academiadecodigo.loginSceneBuilder.service.ServiceRegistry;
import org.academiadecodigo.loginSceneBuilder.model.User;
import org.academiadecodigo.loginSceneBuilder.service.user.UserService;
import org.academiadecodigo.loginSceneBuilder.navigation.Navigation;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by codecadet on 17/11/16.
 */
public class LoginController implements Initializable{

    private boolean isLoginMode;
    private UserService userService;

    @FXML
    private ImageView logo;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label title;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label codeCadetLabel;

    @FXML
    private TextField codeCadetField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private Button registerLoginButton;

    @FXML
    private Hyperlink cancelLink;

    @FXML
    private Button changeModeButton;

    @FXML
    private Label userTakenMessage;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public void clearFields() {
        codeCadetField.setText("");
        passwordField.setText("");
        emailField.setText("");
        userTakenMessage.setText("");
        userTakenMessage.setTextFill(Paint.valueOf("BLACK"));
    }

    public void clickRegisterLoginButton() {
        if (isLoginMode) {
            doLogin();
        } else {
            doRegister();
        }
    }

    public void doLogin() {
        if (!userService.authenticate(codeCadetField.getText(), passwordField.getText())) {
            userTakenMessage.setText("Invalid username or password!");
            userTakenMessage.setTextFill(Paint.valueOf("RED"));
            return;
        }
        userTakenMessage.setTextFill(Paint.valueOf("BLACK"));
        userTakenMessage.setText("Logged in successfully!");
        Navigation.getInstance().loadScreen("home");
        codeCadetField.setText("");
        passwordField.setText("");
        userTakenMessage.setText("");

    }

    public void doRegister() {

        // Execute this when the username is already taken
        String username = codeCadetField.getText();

        if(username.equals("")){
            userTakenMessage.setTextFill(Paint.valueOf("RED"));
            userTakenMessage.setText("You must insert a username!");
            return;
        }

        if ((userService.findByName(username)) != null) {
            userTakenMessage.setTextFill(Paint.valueOf("RED"));
            userTakenMessage.setText("Username already taken!");
            return;
        }

        // Require a password with certain characteristics
        if (passwordField.getText().length() < 5) {
            userTakenMessage.setTextFill(Paint.valueOf("RED"));
            userTakenMessage.setText("The password is too short!");
            return;
        }

        // Require a correct email address type
        if(!validate(emailField.getText())) {
            userTakenMessage.setTextFill(Paint.valueOf("RED"));
            userTakenMessage.setText("Invalid email adress!");
            return;
        }

        // Execute this when the registration was successful
        userService.addUser(new User(username, passwordField.getText(), emailField.getText()));
        userTakenMessage.setTextFill(Paint.valueOf("BLACK"));
        userTakenMessage.setText("Registered successfully!");
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void changeMode() {
        if (isLoginMode) {
            registerMode();
        } else {
            loginMode();
        }
    }

    public void loginMode() {
        registerLoginButton.setText("Login");
        changeModeButton.setText("Don't have an account? Register");
        isLoginMode = true;
        emailLabel.setVisible(false);
        emailField.setVisible(false);
    }

    public void registerMode() {
        registerLoginButton.setText("Register");
        changeModeButton.setText("Already have an account? Login");
        isLoginMode = false;
        emailLabel.setVisible(true);
        emailField.setVisible(true);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //userService = (UserService)ServiceRegistry.getInstance().getService("JdbcUserService");
        //userService = (UserService)ServiceRegistry.getInstance().getService("HibernateUserService");
        userService = (UserService)ServiceRegistry.getInstance().getService(UserService.class.getName());

    }

}
