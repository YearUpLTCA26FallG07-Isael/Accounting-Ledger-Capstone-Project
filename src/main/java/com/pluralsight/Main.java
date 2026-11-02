package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {
    static Scanner myScanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("transactions.csv");
        BufferedReader buff = new BufferedReader(file);
        FileWriter fileWriter = new FileWriter("transactions.csv" , true);
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        System.out.println("Welcome to the Bank!\n");
        char command = ' ';
        while(command != 'X'){
            System.out.println("Enter the letter for the option you want.");
            System.out.println("D) Add Deposit\n" +
                    "\n" +
                    "P) Make Payment\n" +
                    "\n" +
                    "L) Ledger\n" +
                    "\n" +
                    "X) Exit\n");
            System.out.print("Enter option:");
            command = myScanner.next().toUpperCase().charAt(0);
            myScanner.nextLine();
            switch(command){
                case 'D': depositTransaction(buff , buffWriter);
                case 'P': paymentTransaction(buff ,buffWriter);
                case 'L': ledger(buff, buffWriter);

            }
        }
        System.out.println("Thank you for visiting the bank! Have a good day!");
        buffWriter.close();
        buff.close();


    }
    public static void depositTransaction(BufferedReader buff , BufferedWriter buffWriter) throws IOException {
        LocalDate date = LocalDate.now();

        LocalTime times = LocalTime.now();

        String time = times.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.print("Enter the description:");

        String description = myScanner.nextLine();

        System.out.print("Enter the vendor name:");

        String vendor = myScanner.nextLine();

        System.out.print("Enter the amount you would like to deposit:");

        double amount = myScanner.nextDouble();

        while(amount < 0){
            System.out.print("cannot deposit negative amounts pls try again");
            System.out.println("Enter the amount you would like to deposit:");
            amount = myScanner.nextDouble();
        }

        Transactions list = new Transactions(date , time ,description , vendor , amount);

        buffWriter.write(list.getDate() + "|" + list.getTime() + "|" + list.getDescription() + "|" + list.getVendor() + "|" + list.getAmount() + "\n");



    }
    public static void paymentTransaction(BufferedReader buff , BufferedWriter buffWriter) throws IOException {
        LocalDate date = LocalDate.now();

        LocalTime times = LocalTime.now();

        String time = times.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.print("Enter the description:");

        String description = myScanner.nextLine();

        System.out.print("Enter the vendor name:");

        String vendor = myScanner.nextLine();

        System.out.print("Enter the amount you have to pay:");

        double amount = myScanner.nextDouble();

        while(amount > 0){
            System.out.print("cannot pay positive amounts please try again");
            System.out.println("Enter the amount you have to pay:");
            amount = myScanner.nextDouble();
        }

        Transactions list = new Transactions(date , time ,description , vendor , amount);

        buffWriter.write(list.getDate() + "|" + list.getTime() + "|" + list.getDescription() + "|" + list.getVendor() + "|" + list.getAmount() + "\n");



    }
    public static void ledger(BufferedReader buff, BufferedWriter buffWriter) throws IOException {
        String input;
        int num = 1;
            while((input = buff.readLine()) != null) {
                System.out.println(input);
            }
    }
}