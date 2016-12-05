package org.academiadecodigo.loginSceneBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.academiadecodigo.loginSceneBuilder.service.ServiceRegistry;
import org.academiadecodigo.loginSceneBuilder.service.user.UserService;
import org.academiadecodigo.loginSceneBuilder.service.user.UserServiceImpl;
import org.academiadecodigo.loginSceneBuilder.model.dao.hibernate.HibernateUserDao;
import org.academiadecodigo.loginSceneBuilder.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.loginSceneBuilder.navigation.Navigation;
import org.academiadecodigo.loginSceneBuilder.persistence.jdbc.ConnectionManager;
import org.academiadecodigo.loginSceneBuilder.persistence.hibernate.HibernateTransactionManager;

/**
 * Created by codecadet on 17/11/16.
 */
public class Main extends Application{

    private FXMLLoader fxmlLoader;

    private Parent root;

    private ConnectionManager connectionManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {

        //connectionManager = new ConnectionManager();

        //ServiceRegistry.getInstance().addService(new JdbcUserService(connectionManager));
        //ServiceRegistry.getInstance().addService(new HibernateUserService());

        UserService userService = new UserServiceImpl(new HibernateUserDao(), new HibernateTransactionManager());
        ServiceRegistry.getInstance().addService(userService);

        //((LoginController) Navigation.getInstance().getController("login")).setUserService(new JdbcUserService(connectionManager));

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //connectionManager.close();
        HibernateSessionManager.close();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // set the primary stage
        Navigation.getInstance().setStage(primaryStage);

        // load the first scene
        Navigation.getInstance().loadScreen("login");

    }

}
