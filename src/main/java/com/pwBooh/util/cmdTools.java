package com.pwBooh.util;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pwBooh.pojos.UserData.getPasswordLength;

public class cmdTools {


    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<String> printList = new ArrayList<>();

    public static ArrayList<String> getPrintList() {
        return printList;
    }

    public static void setPrintList(ArrayList<String> printList) {
        cmdTools.printList = printList;
    }

    public static ArrayList<String> getList() {
        return list;
    }

    public static String getShowUserscmd() {
        return "net user";
    }
    public static void parseUsersFromCmd(String output) {
        String out = output.substring(output.lastIndexOf("-") + 1, output.length());
        String test = out.replaceAll("\n", "").replaceAll("Команда выполнена успешно.", "");
        String abc[] = test.split(" ");

        for (String anAbc : abc) {
            if (!anAbc.equals(""))
                list.add(anAbc);
        }
    }

    public static void printOutput() {
        for (String s : list
                ) {
            System.out.println(s);
        }
    }
    public static void deleteUserFromList(ArrayList<String> list) {
        String[] pattern = {"Pavel", "Администратор", "Гость" };
        for (String s : pattern
                ) {
            Pattern p3 = Pattern.compile(s);
            for (int i = 0; i < list.size(); i++) {
                Matcher matcher = p3.matcher(list.get(i));
                while (matcher.find()) {
                    list.remove(i);
                }
            }
        }
    }

    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream(), "IBM866"));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    public static void changePwOnServer(ArrayList<String> list) {
        printList.clear();
        for (String login : list
                ) {
            String pw = generatePassword(getPasswordLength());
            executeCommand("net user " + login + " " + pw);
            printList.add("Логин: " + login + " Пароль: " + pw);
        }
    }
    public static void printUsersData(Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        final double scaleX = (pageLayout.getPrintableWidth()) / node.getBoundsInParent().getWidth();
        final double scaleY = (pageLayout.getPrintableHeight()) / node.getBoundsInParent().getHeight();
        node.getTransforms().add(new Scale(scaleX, scaleY));
        PrinterJob job =PrinterJob.createPrinterJob();
        if (job != null ){
            boolean success = job.printPage(node);
//            System.out.println("printed");
            if (success){
//                System.out.println(success);
                job.endJob();
            }
        }
//        for (String s : getPrintList()
//                ) {
//            System.out.println("-------------------------------------------------------");
//            System.out.println("|                                                     |");
//            System.out.println("|            " + s + "            |");
//            System.out.println("|                                                     |");
//            System.out.println("-------------------------------------------------------");
//        }
//        System.out.println("\n\n");
    }
    //Метод генерирует пароли
    private static String generatePassword(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}


