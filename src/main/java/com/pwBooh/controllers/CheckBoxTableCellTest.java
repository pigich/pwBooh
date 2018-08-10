package com.pwBooh.controllers;

import com.pwBooh.MainApp;
import com.pwBooh.pojos.Logins;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pwBooh.MainApp.*;
import static com.pwBooh.pojos.UserData.*;
import static com.pwBooh.util.ReadWrite.readDataFromFile;
import static com.pwBooh.util.ReadWrite.writeDataToFile;
import static com.pwBooh.util.cmdTools.*;


public class CheckBoxTableCellTest {
    @FXML
    public Button printBtn, saveListBtn, generatePwBtn;
    @FXML
    public TableColumn<Logins, String> loginsColumn;
    @FXML
    public TableColumn<Logins, Boolean> selectedColumn;
    @FXML
    public Label errorMessage;
    @FXML
    private TableView<Logins> tableView;
    private MainApp mainApp;
    @FXML
    public GridPane gridPane;

    public CheckBoxTableCellTest() {
    }


    /**
     * Проверка на наличие конфигурационного файла
     */
    private static Boolean checkUserFile(String userFile) throws IOException {
        f = new File(userFile);
        boolean var;
        var = f.exists();
        return var;
    }

    public static void checkIfFileExist() {
        File f = new File(getOutputFile());
        if (f.exists() && !f.isDirectory()) {
            System.out.println("config file does not exist");
        } else readDataFromFile();
    }

    @FXML
    private void initialize() {
        Starting();
        selectedColumn.setEditable(true);
        tableView.setEditable(true);
// set where to read values from
        loginsColumn.setCellValueFactory(p -> p.getValue().loginProperty());
        selectedColumn.setCellValueFactory(p -> p.getValue().checkedProperty());

//        Вызывается когда мы меняем чекбокс
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Integer param) {
//                System.out.println("Cours " + loginsData.get(param).getLogin() +
// " changed value to " + loginsData.get(param).isChecked());
                finalList.clear();
                for (Logins aLoginsData : loginsData) {
                    if (aLoginsData.isChecked()) {
                        finalList.add(aLoginsData.getLogin());
                    }
                }
                return loginsData.get(param).checkedProperty();
            }
        }));
//        loginsColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
//        selectedColumn.setCellValueFactory(cellData -> cellData.getValue().checkedProperty());
//        loginsColumn.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<Logins, String>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<Logins, String> t) {
//                        ((Logins) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setLogin(t.getNewValue());
//                    }
//                }
//        );


//        loginsColumn.setCellFactory(column -> {
//            return new TableCell<Logins, String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (item == null || empty) {
//
//                        setStyle("");
//                    } else {
//                        getItem();
//                        setTextFill(Color.CHOCOLATE);
//                        setStyle("-fx-background-color: yellow");
//                    }
//                }
//            };
//        });
//        selectedColumn.setCellFactory(column -> {
//            return new TableCell<Logins, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (item == null || empty) {
//                        setText(null);
//                    } else {
//                        getItem();
//
//                    }
//                }
//            };
//        });

//        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer,
//                        ObservableValue<Boolean>>() {
//            @Override
//            public ObservableValue<Boolean> call(Integer param) {
//                System.out.println("Cours " + loginsData.get(param).getLogin() + " changed value to " + loginsData.get(param).isChecked());
//                for (Integer i = 0; i < loginsData.size(); i++) {
//                    if (i != param && loginsData.get(param).isChecked()) {
//                        loginsData.get(i).setChecked(false);
//                        System.out.println("unchecked " + loginsData.get(param).checkedProperty());
//                    } // if
//                } //  for
//                System.out.println("checked " + loginsData.get(param).checkedProperty());
//                return loginsData.get(param).checkedProperty();
//            }
//        }));
//        selectedColumn.setCellFactory(
//                CheckBoxTableCell.forTableColumn(index -> loginsData.get(index).checkedProperty()));
//        loginsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        loginsColumn.setCellFactory(TextFieldTableCell.forTableColumn((Callback<Integer, ObservableValue<String>>) param -> {
//            for (Integer i = 0; i < loginsData.size(); i++) {
//                if (i != param && !loginsData.get(param).getLogin().isEmpty()) {
//                    loginsData.get(i).setChecked(false);
//                    System.out.println("unchecked " + loginsData.get(param).loginProperty());
//                } // if
//            } //  for
//            System.out.println("checked " + loginsData.get(param).loginProperty());
//            return loginsData.get(param).loginProperty();
//        }));

//        selectedColumn.setCellValueFactory(cellData -> cellData.getValue().checkedProperty());
//        LoginsData = FXCollections.observableArrayList(new Callback<Logins, Observable[]>() {
//
//            @Override
//            public Observable[] call(Logins param) {
//                return new Observable[]{param.checkedProperty()};
//            }
//        });
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<Logins> filteredData = new FilteredList<>(loginsData, p -> true);
//
//        // 3. Wrap the FilteredList in a SortedList.
//        SortedList<Logins> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        //    Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        tableView.setItems(sortedData);
//        tableView.setItems(loginsData);
//        loginsData.addListener((ListChangeListener.Change<? extends Logins> c) -> {
//            while (c.next()) {
//                if (c.wasUpdated()) {
//                    System.out.println("Updated:");
//                    loginsData.subList(c.getFrom(), c.getTo()).forEach(System.out::println);
//                    System.out.println();
//                }
//            }
//        });

        tableView.getColumns().setAll(loginsColumn, selectedColumn);

        MainApp.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!errorMessage.getText().equals(""))
                    errorMessage.setText("");
            }
        });

//            errorMessage.setText("");
//        anchorPane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//                        @Override
//                        public void handle(MouseEvent mouseEvent) {
//                            System.out.println("mouse click detected! " + mouseEvent.getSource());
//                        }
//                    });
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        tableView.setItems(getLoginsData());
    }

    private void Starting() {
        try {

            if (checkUserFile(getOutputFile())) {
                loginsData = readDataFromFile();
            } else {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Файл настроек не найден,\n Откроется список логинов из системы");
                firstInitialization();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    public void printList() {
//        printUsersData(PrintDialogPage);
//    }

    @FXML
    public void saveList() {
        writeDataToFile(errorMessage, loginsData);
    }

    @FXML
    public void generatePasswords() {
//        for (String s : getFinalList()
//                ) {
//            System.out.println(s);
//        }
        changePwOnServer(getFinalList());
//        for (String s :
//                getPrintList()) {
//            System.out.println(s);
//        }
        showPrintDialog();
//        putListPrintGrid(getPrintList());
    }

    private void firstInitialization() {
        parseUsersFromCmd(executeCommand(getShowUserscmd()));
        createObservableLoginsData(getList());
    }


    private void createObservableLoginsData(ArrayList<String> list) {
        List<Logins> loginsList = new ArrayList<>();
        for (String s : list
                ) {
            loginsList.add(new Logins(s, false));
        }
        loginsData = FXCollections.observableArrayList(loginsList);
    }
//    private void putListOfLoginsToGrid(ArrayList<String> list) {
//
//        for (int i = 0; i < list.size(); i++) {
//            CheckBox checkBox = new CheckBox();
//            checkBox.setText(list.get(i));
//            System.out.println(checkBox.getText());
////            gridPane.setGridLinesVisible(true);
//            gridPane.add(checkBox, 0, 0 + i);
//        }
//    }
}

