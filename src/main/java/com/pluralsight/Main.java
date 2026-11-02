package com.pluralsight;
import java.io.*;
import java.util.*;
public class Main {
    static Scanner myScanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("transactions.csv");
        BufferedReader buff = new BufferedReader(file);
        FileWriter fileWriter = new FileWriter("transactions.csv");
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        int count = 0;
        char command = ' ';
        while(command != 'X'){
            System.out.println("Welcome to the Bank!\n");
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
            }
        }
        System.out.println("Thank you for visiting the bank! Have a good day!");
        buffWriter.close();
        buff.close();


    }
    public static void depositTransaction(BufferedReader buff , BufferedWriter buffWriter) throws IOException {
        System.out.print("Enter the date:");

        String date = myScanner.nextLine();

        System.out.print("Enter the time:");

        String time = myScanner.nextLine();

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
}