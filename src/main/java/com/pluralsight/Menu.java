package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Menu {
    public static ArrayList<Transactions> transactions = new ArrayList<>();
    public static void menuMethod() {


        FileReader file = null;
        try {
            file = new FileReader("transactions.csv");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


        BufferedReader buff = new BufferedReader(file);

        populateTransaction(buff);


        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("transactions.csv" , true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedWriter buffWriter = new BufferedWriter(fileWriter);

        char command = ' ';
        while(command != 'X') {

            command = Console.displayHomeScreen();

            switch (command) {

                case 'D': depositTransaction(buffWriter);
                break;

                case 'P': paymentTransaction(buffWriter);
                break;

                case 'L': Ledger.ledger(buff, buffWriter);
                break;

                case 'X': displayExitScreen();
                break;

                default: Console.showMessage("Invalid option");

            }
        }
        try {
            buffWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            buff.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void populateTransaction(BufferedReader buff) {

        try {
            String line;

            while ((line = buff.readLine()) != null) {
                transactions.add(new Transactions(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void depositTransaction(BufferedWriter buffWriter) {
        LocalDate date = LocalDate.now();

        String time = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("hh:mm a"));

        String description = Console.getDescription();
        String vendor = Console.getVendor();
        double amount = Console.getDepositAmount();

        Transactions transaction = new Transactions(date, time, description, vendor, amount);

        transactions.add(transaction);

        try {
            buffWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            buffWriter.newLine();
            buffWriter.flush();
            Console.showMessage("Deposit added successfully.");
        } catch (IOException e) {
            Console.showMessage("Error saving deposit transaction.");
        }
         Console.pressEnterToContinue();
    }
    public static void paymentTransaction(BufferedWriter buffWriter) {
        LocalDate date = LocalDate.now();

        String time = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("hh:mm a"));

        String description = Console.getDescription();
        String vendor = Console.getVendor();
        double amount = Console.getPaymentAmount();

        while (amount >= 0) {
            System.out.println("Payment amount must be negative.");
            amount = Console.getPaymentAmount();
        }

        Transactions transaction = new Transactions(date, time, description, vendor, amount);

        transactions.add(transaction);

        try {
            buffWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            buffWriter.newLine();
            buffWriter.flush();

            System.out.println("Payment recorded successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Console.pressEnterToContinue();
    }
    public static void displayExitScreen() {
        System.out.println("""
            ==================================================
                       THANK YOU FOR USING
                         BANK ACCOUNT LEDGER
            ==================================================

                    Your session has ended.

                      Have a great day!

            ==================================================
            """);
    }
}
