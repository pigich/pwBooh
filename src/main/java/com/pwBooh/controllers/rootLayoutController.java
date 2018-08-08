package com.pwBooh.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import static com.pwBooh.MainApp.showConfigEditDialog;


public class rootLayoutController {
    @FXML
    public MenuItem aboutButton;

    @FXML
    public MenuItem SetConfig;
    public MenuItem help;

    @FXML
    private void initialize() {
    }

    @FXML
    private void aboutButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText(null);
        alert.setHeight(300);
        alert.setGraphic(null);
        alert.setContentText("This program was written by:\n" +
                "Piga Pavlo  email: pigich09@gmail.com\n" );
        alert.showAndWait();
    }

    @FXML
    private void helpButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Помощь");
        alert.setHeaderText(null);
        alert.setHeight(300);
        alert.setGraphic(null);
        alert.setContentText("Здесь будет информация \n" +
                "как пользоваться программой\n" );
        alert.showAndWait();
    }

    public void setConfigs(ActionEvent event) {
        showConfigEditDialog();
    }
}

