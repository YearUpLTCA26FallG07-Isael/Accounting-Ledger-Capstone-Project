package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static ArrayList<Transactions> transactions = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader("transactions.csv");

        BufferedReader buff = new BufferedReader(file);

        populateTransaction(buff);


        FileWriter fileWriter = new FileWriter("transactions.csv" , true);

        BufferedWriter buffWriter = new BufferedWriter(fileWriter);

        char command = ' ';

       do{
           command = Console.displayHomeScreen();

            switch(command){
                case 'D': depositTransaction(buff , buffWriter);
                break;
                case 'P': paymentTransaction(buff ,buffWriter);
                break;
                case 'L': ledger(buff, buffWriter);
                break;
                case 'X': Console.showMessage("Thank you for visiting the bank! Have a good day!");
                break;
                default:
                    Console.showMessage("Invalid option");

            }
        }while(command != 'X');

        buffWriter.close();

        buff.close();


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
    public static void depositTransaction(BufferedReader buff , BufferedWriter buffWriter) throws IOException {
        LocalDate date = LocalDate.now();

        String time = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("hh:mm a"));

        String description = Console.getDescription();
        String vendor = Console.getVendor();
        double amount = Console.getDepositAmount();

        Transactions transaction =
                new Transactions(date, time, description, vendor, amount);

        transactions.add(transaction);

        buffWriter.write(
                date + "|" +
                        time + "|" +
                        description + "|" +
                        vendor + "|" +
                        amount
        );

        buffWriter.newLine();
        buffWriter.flush();

        Console.showMessage("Deposit added successfully.");
        Console.pressEnterToContinue();
    }
    public static void paymentTransaction(BufferedReader buff , BufferedWriter buffWriter) throws IOException {
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

        Transactions transaction =
                new Transactions(date, time, description, vendor, amount);

        transactions.add(transaction);

        buffWriter.write(
                date + "|" +
                        time + "|" +
                        description + "|" +
                        vendor + "|" +
                        amount
        );

        buffWriter.newLine();
        buffWriter.flush();

        System.out.println("Payment recorded successfully.");
        Console.pressEnterToContinue();
    }
    public static void ledger(BufferedReader buff, BufferedWriter buffWriter) throws IOException {
        char command;
        do {
            command = Console.displayLedgerMenu();
            switch (command) {

                case 'A':
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        System.out.println(transactions.get(i));
                    }
                    break;

                case 'D':
                    for (int i = transactions.size() - 1; i >= 0; i--) {

                        if (transactions.get(i).getAmount() > 0) {
                            System.out.println(transactions.get(i));
                        }

                    }
                    break;

                case 'P':
                    for (int i = transactions.size() - 1; i >= 0; i--) {

                        if (transactions.get(i).getAmount() < 0) {
                            System.out.println(transactions.get(i));
                        }

                    }
                    Console.pressEnterToContinue();
                    break;

                case 'R':
                    reportScreen();
                    break;

                case 'H':
                    System.out.println("Returning to home screen...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (command != 'H');


    }
    public static void reportScreen(){
        int command;

        do {

            command = Console.displayReportMenu();

            switch (command) {

                case 1:
                    monthToDate(transactions);
                    break;

                case 2:
                    previousMonth(transactions);
                    break;

                case 3:
                    yearToDate(transactions);
                    break;

                case 4:
                    previousYear(transactions);
                    break;

                case 5:
                    String vendor = Console.getVendorSearch();
                    searchByVendor(transactions, vendor);
                    break;

                case 0:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (command != 0);
    }
    public static void monthToDate(List<Transactions> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);

        transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstDay)
                        && !t.getDate().isAfter(today))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> Console.showMessage(t.toString()));
        Console.pressEnterToContinue();
    }
    public static void previousMonth(List<Transactions> transactions) {
        LocalDate today = LocalDate.now();

        LocalDate firstDayPrevMonth =
                today.minusMonths(1).withDayOfMonth(1);

        LocalDate lastDayPrevMonth =
                firstDayPrevMonth.withDayOfMonth(
                        firstDayPrevMonth.lengthOfMonth());

        transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstDayPrevMonth)
                        && !t.getDate().isAfter(lastDayPrevMonth))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> Console.showMessage(t.toString()));
        Console.pressEnterToContinue();
    }
    public static void yearToDate(List<Transactions> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayYear = today.withDayOfYear(1);

        transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstDayYear)
                        && !t.getDate().isAfter(today))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> Console.showMessage(t.toString()));
        Console.pressEnterToContinue();
    }
    public static void previousYear(List<Transactions> transactions) {
        int previousYear = LocalDate.now().getYear() - 1;

        transactions.stream()
                .filter(t -> t.getDate().getYear() == previousYear)
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> Console.showMessage(t.toString()));
        Console.pressEnterToContinue();
    }
    public static void searchByVendor(List<Transactions> transactions, String vendor) {

        transactions.stream()
                .filter(t -> t.getVendor()
                        .toLowerCase()
                        .contains(vendor.toLowerCase()))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> Console.showMessage(t.toString()));
        Console.pressEnterToContinue();
    }
 }
