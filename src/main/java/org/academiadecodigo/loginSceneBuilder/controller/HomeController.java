package org.academiadecodigo.loginSceneBuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.academiadecodigo.loginSceneBuilder.navigation.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 17/11/16.
 */
public class HomeController implements Initializable{

    @FXML
    private MenuItem backMenuItem;

    public void goBack(){
        Navigation.getInstance().back();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
