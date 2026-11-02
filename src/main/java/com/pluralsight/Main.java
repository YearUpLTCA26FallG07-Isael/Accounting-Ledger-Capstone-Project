package com.pluralsight;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
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
            command = myScanner.next().charAt(0);


        }



    }
}