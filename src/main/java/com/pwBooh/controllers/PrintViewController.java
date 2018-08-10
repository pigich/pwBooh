package com.pwBooh.controllers;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.pwBooh.MainApp.PrintDialogPage;
import static com.pwBooh.MainApp.gridPane;
import static com.pwBooh.util.cmdTools.printUsersData;

public class PrintViewController {
    @FXML
    public Button printBtn;
    private Stage printDialogStage;

    public static GridPane putListPrintGrid(ArrayList<String> list) {
        GridPane printGrid = new GridPane();
        printGrid.setGridLinesVisible(true);
        ColumnConstraints column2 = new ColumnConstraints(100, 220, Double.MAX_VALUE);
        printGrid.getColumnConstraints().addAll(column2, column2);
        RowConstraints row = new RowConstraints(30, 40, Double.MAX_VALUE);

        for (int i = 0; i < list.size(); i++) {
            printGrid.getRowConstraints().add(row);
            for (int j = 0; j <= 1; j++) {
                Label label = new Label();
                label.setText(list.get(i));
                printGrid.add(label, j, i);
                GridPane.setHalignment(label, HPos.CENTER); //выравнивание метки в ячейке по горизонтале
                GridPane.setValignment(label, VPos.CENTER); //выравнивание метки в ячейке по вертикали
            }
        }
        return printGrid;
    }

    public void setPrintStage(Stage printDialogStage) {
        this.printDialogStage = printDialogStage;
    }

    @FXML
    public void printList() {
        printUsersData(gridPane);
    }
}
