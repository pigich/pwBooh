package com.pwBooh;


import com.pwBooh.controllers.CheckBoxTableCellTest;
import com.pwBooh.controllers.PrintViewController;
import com.pwBooh.controllers.confDialogController;
import com.pwBooh.pojos.Logins;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.pwBooh.controllers.PrintViewController.putListPrintGrid;
import static com.pwBooh.util.cmdTools.getPrintList;


public class MainApp extends Application {

    private final static String PROG_VERSION = "0.13";
    public static AnchorPane anchorPane;
    public static AnchorPane PrintDialogPage;
    public static GridPane gridPane;
    public static ObservableList<Logins> loginsData = FXCollections.observableArrayList();
    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static Scene scene;

    public MainApp() {
    }

    /**
     * Возвращает данные в виде наблюдаемого списка адресатов.
     */
    public static ObservableList<Logins> getLoginsData() {
        return loginsData;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) throws IOException {

        launch(args);
    }


    /**
     * Диалог выбора почтового провайдера через кого будет отправляться почта.
     */
    public static boolean showConfigEditDialog() {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ConfigEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование конфигурационного файла");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Передаём адресата в контроллер.
            confDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void showPrintDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/PrintView.fxml"));
            PrintDialogPage = loader.load();
            Stage printDialogStage = new Stage();
            printDialogStage.setTitle("Список пользователей с паролями");
            printDialogStage.initModality(Modality.WINDOW_MODAL);
            printDialogStage.initOwner(primaryStage);
            printDialogStage.setResizable(false);
            gridPane = putListPrintGrid(getPrintList());
            GridPane.setHalignment(gridPane, HPos.CENTER);
            PrintDialogPage.getChildren().addAll(gridPane);
            Scene scene = new Scene(PrintDialogPage);
            printDialogStage.setScene(scene);
            PrintViewController controller = loader.getController();
            controller.setPrintStage(printDialogStage);
            printDialogStage.show();
//            printUsersData(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("pwBooh" + PROG_VERSION);
        MainApp.primaryStage.setMinHeight(600);
        MainApp.primaryStage.setMinWidth(400);
        initRootLayout();
        try {
            showFirstView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализирует корневой макет.
     */
    private void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RootLayout.fxml"));
            rootLayout = loader.load();
            // Отображаем сцену, содержащую корневой макет.
            scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загружаем сведения
     */
    private void showFirstView() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/TableView.fxml"));
            anchorPane = loader.load();
            rootLayout.setCenter(null);
            rootLayout.setBottom(anchorPane);
            primaryStage.setResizable(false);
            CheckBoxTableCellTest controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }

}

