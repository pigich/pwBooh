package com.pwBooh.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static com.pwBooh.pojos.UserData.setPasswordLength;

public class confDialogController {

    @FXML
    public TextField passwordLength;
    private boolean okClicked = false;
    @FXML
    private Label errorMessage;
    private Stage printDialogStage;


    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        Platform.runLater(() -> passwordLength.requestFocus());
        passwordLength.addEventFilter(KeyEvent.KEY_TYPED, symbol_Validation(errorMessage));
        passwordLength.textProperty().addListener((observable) ->
                errorMessage.setText(""));
        passwordLength.textProperty().addListener((observable) ->
                length_Validation());

//        passwordLength.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                String newValue) {
//                if ( Integer.parseInt(newValue)  > 20 )
////                    (Integer.parseInt(passwordLength.getText()) == 0))
//                {
//                    errorMessage.setText("Длина пароля не должна\nпривышать двадцати символов");
//                }
//
////                if (newValue.matches("\\d*")) {
////                    int value = Integer.parseInt(newValue);
////                } else {
////                    passwordLength.setText(oldValue);
////                }
//
//
//            }
//        });
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage stage
     */
    public void setDialogStage(Stage dialogStage) {
        this.printDialogStage = dialogStage;
    }

    /**
     * @return Returns true, если пользователь кликнул OK, в другом случае false.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (userLogging()) {
            okClicked = true;
            printDialogStage.close();
        }
    }


    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        printDialogStage.close();
    }

    @FXML
    private boolean userLogging() {
        if (passwordLength.getLength() == 0) {
            errorMessage.setText("Введите значение\n длины пароля!");
        } else if (Integer.parseInt(passwordLength.getText()) > 20) {
            errorMessage.setText("Длина пароля не должна\nбыть больше двадцати");
        }
        else {
            setPasswordLength(Integer.parseInt(passwordLength.getText()));
            return true;
        }
        return false;
    }

    /**
     * Проверка на допустимые символы в логине
     */
    private EventHandler<KeyEvent> symbol_Validation(Label errorMessage) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                String test = e.getText();
                if (e.getCharacter().matches("[1-9]")) {
                    errorMessage.setText("");
                } else if (e.getSource() == KeyCode.BACK_SPACE) {
                    errorMessage.setText("");
                } else if (!txt_TextField.getText().isEmpty() && (Integer.parseInt(txt_TextField.getText()) > 20)) {
                    e.consume();
                    errorMessage.setText("Длина пароля не должна\nбыть больше двадцати");
                } else {
                    e.consume();
                    errorMessage.setText("Только цифры!");
                }
            }
        };
    }

    private void length_Validation() {

    }
}
