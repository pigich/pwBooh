package com.pwBooh.util;

import com.pwBooh.pojos.Logins;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite {
    private static BufferedReader br = null;

    public static ObservableList<Logins> readDataFromFile() {
        ObservableList<Logins> loginsData = FXCollections.observableArrayList();
        try {
            FileInputStream inputStream = new FileInputStream("config.txt");
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "windows-1251"));
            String line;
            List<Logins> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String cvsSplitBy = " ";
                String[] companyData = line.split(cvsSplitBy);
                String login = companyData[0];
                String checked = companyData[1];
                list.add(new Logins(login, Boolean.valueOf(checked)));
            }
            loginsData = FXCollections.observableList(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return loginsData;
    }

    public static void writeDataToFile(Label label, ObservableList<Logins> loginsData) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("config.txt"), "windows-1251")))) {
            for (Logins aLoginsData : loginsData) {
                out.println(aLoginsData);
            }
            label.setTextFill(Color.GREEN);
            label.setText("Файл настроек сохранен");
//            for (int j = 0; j < loginsData.size(); j++) {
//                out.println(loginsData.get(j));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
