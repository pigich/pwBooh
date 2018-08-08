package com.pwBooh.util;

import java.io.*;

import static com.pwBooh.pojos.UserData.getOutputFile;

public class ErrorHandler {


    static void PrintFile() {
        String line;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(
                    new FileInputStream(getOutputFile()));
        } catch (FileNotFoundException e) {
            System.out.println("файл " + getOutputFile() + " не найден");
        }
        BufferedReader br = new BufferedReader(isr);
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Unable to close file: " + getOutputFile());
            }
        }
    }
}
