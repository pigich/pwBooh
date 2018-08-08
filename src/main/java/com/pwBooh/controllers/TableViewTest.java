//package com.pwBooh.controllers;
//
//
//import javafx.application.Application;
//import javafx.beans.InvalidationListener;
//import javafx.beans.Observable;
//import javafx.beans.property.BooleanProperty;
//import javafx.beans.property.SimpleBooleanProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ListChangeListener;
//import javafx.collections.ObservableList;
//import javafx.scene.Scene;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.CheckBoxTableCell;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//public class TableViewTest extends Application {
//
//    private ObservableList<Logins> LoginsData;//= FXCollections.observableArrayList();
//    private TableView<Logins> tableView;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//
//    public void start(Stage primaryStage) {
//
//        this.tableView = new TableView<Logins>();
//        final TableColumn<Logins, String> Logins = new TableColumn<Logins, String>("Logins");
//        final TableColumn<Logins, Boolean> checkedCol = new TableColumn<Logins, Boolean>("Checked");
//
//        final List<Logins> items = Arrays.asList(
//                new Logins("Analyse"),
//                new Logins("Analyse TP"),
//                new Logins("Thermo TP"),
//                new Logins("Chimie"));
//        this.LoginsData = FXCollections.observableArrayList(new Callback<Logins, Observable[]>() {
//
//            @Override
//            public Observable[] call(Logins param) {
//                return new Observable[]{param.checkedProperty()};
//            }
//        });
//        LoginsData.addAll(items);
//        tableView.setItems(this.LoginsData);
//
//        tableView.getColumns().addAll(Logins, checkedCol);
//
//        Logins.setCellValueFactory(new PropertyValueFactory<Logins, String>("Logins"));
//
//        checkedCol.setCellValueFactory(new PropertyValueFactory<Logins, Boolean>("checked"));
//
//        checkedCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkedCol));
//        checkedCol.setEditable(true);
//        tableView.setEditable(true);
//
//        LoginsData.addListener(new ListChangeListener<Logins>() {
//
//            @Override
//            public void onChanged(ListChangeListener.Change<? extends Logins> c) {
//                while (c.next()) {
//                    if (c.wasUpdated()) {
//                        System.out.println("Logins " + items.get(c.getFrom()).getLogin() + " changed value to " + items.get(c.getFrom()).isChecked());
//                    }
//                }
//            }
//        });
//        final BorderPane root = new BorderPane();
//        root.setCenter(tableView);
//
//        Scene scene = new Scene(root, 300, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public class Logins {
//        private StringProperty Logins;
//        private BooleanProperty checked;
//
//         Logins(String Logins) {
//            this.Logins = new SimpleStringProperty(Logins);
//            this.checked = new SimpleBooleanProperty(false);
//        }
//
//         String getLogin() {
//            return Logins.get();
//        }
//
//        public void setLogin(String logins) {
//            this.Logins.set(logins);
//        }
//
//         boolean isChecked() {
//            return checked.get();
//        }
//
//        public void setChecked(boolean checked) {
//            this.checked.set(checked);
//        }
//
//        public StringProperty loginProperty() {
//            return Logins;
//        }
//
//
//         BooleanProperty checkedProperty() {
//            return checked;
//        }
//    }
//
//
//}
//
////https://stackoverflow.com/questions/48590054/javafx-tableview-how-to-add-a-listener-to-checkbox-column
////https://stackoverflow.com/questions/7217625/how-to-add-checkboxs-to-a-tableview-in-javafx
//// https://stackoverflow.com/questions/28671132/javafx-checkboxtablecell-get-actionevent-when-user-check-a-checkbox/28671914
//// https://stackoverflow.com/questions/28351008/javafx-8-tableview-selection-with-checkbox