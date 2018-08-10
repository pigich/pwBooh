package com.pwBooh.pojos;

import java.io.File;
import java.util.ArrayList;

public class UserData {

    private static int passwordLength = 6;
    public static File f;
    private static String src = System.getProperty("user.dir");
    private static String OUTPUT_FILE = src + "\\" + "config.txt";
    public static ArrayList<String> finalList = new ArrayList<>();

    public static ArrayList<String> getFinalList() {
        return finalList;
    }

    public UserData() {
    }

    public static int getPasswordLength() {
        return passwordLength;
    }

    public static void setPasswordLength(int passwordLength) {
        UserData.passwordLength = passwordLength;
    }

    public static String getOutputFile() {
        return OUTPUT_FILE;
    }

}
