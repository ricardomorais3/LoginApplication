package org.academiadecodigo.loginSceneBuilder.navigation;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by codecadet on 17/11/16.
 */
public class Navigation {

    // static instance of this class
    private static Navigation instance = null;

    private final int MIN_WIDTH = 403; // window width
    private final int MIN_HEIGHT = 422; // window height
    private final String VIEW_PATH = "/view";

    private LinkedList<Scene> scenes = new LinkedList<Scene>(); // Navigation History
    private Map<String, Initializable> controllers = new HashMap<>(); //Container of controllers

    private Stage stage; // reference to the application window

    // private constructor so its not possible to instantiate from outside
    private Navigation() {
    }

    // get a controller from the controllers map
    public Initializable getController(String key){
        return controllers.get(key);
    }

    // static method that returns the instance
    public static synchronized Navigation getInstance() {

        // the instance is created only the first time this method is called
        if(instance == null) {
            instance = new Navigation();
        }

        // it always return the same instance, there is no way to have another one
        return instance;
    }

    public void loadScreen(String view) {
        try {

            // Instantiate the org.academiadecodigo.sceneBuilderFxml.view and the org.academiadecodigo.sceneBuilderFxml.controller
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource(VIEW_PATH + "/" + view + ".fxml"));

            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    return controllers.get(param.getSimpleName());
                }
            });

            Parent root = fxmlLoader.load();

            //Store the org.academiadecodigo.sceneBuilderFxml.controller
            //controllers.put(view, (Initializable) fxmlLoader.getController());

            // Create a new scene and add it to the stack
            Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
            scenes.push(scene);

            // Put the scene on the stage
            setScene(scene);

        } catch (IOException e) {
            System.out.println("Failure to load org.academiadecodigo.sceneBuilderFxml.view " + view + " : " + e.getMessage());
        }
    }

    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        // remove the current scene from the stack
        scenes.pop();

        // load the scene at the top of the stack
        setScene(scenes.peek());
    }

    private void setScene(Scene scene) {

        // set the scene
        stage.setScene(scene);

        // show the stage to reload
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setControllers(Map controllers) {
        this.controllers = controllers;
    }
}
