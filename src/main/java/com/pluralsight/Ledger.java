package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


import static com.pluralsight.Menu.transactions;

public class Ledger {
    public static void ledger(BufferedReader buff, BufferedWriter buffWriter) {
        char command = ' ';
        while(command != 'H'){
            command = Console.displayLedgerMenu();
            switch (command) {

                case 'A':
                    Console.transactionsMenu();
                    System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                            "Date", "Time", "Description", "Vendor", "Amount");

                    System.out.println("----------------------------------------------------------------");
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transactions t = transactions.get(i);

                        System.out.printf("%-12s %-10s %-15s %-15s %10.2f%n",
                                t.getDate(),
                                t.getTime(),
                                t.getDescription(),
                                t.getVendor(),
                                t.getAmount());
                    }
                    Console.transcationsClose();
                    Console.pressEnterToContinue();
                    break;

                case 'D':
                    Console.depositMenu();
                    System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                            "Date", "Time", "Description", "Vendor", "Amount");

                    System.out.println("----------------------------------------------------------------");
                    for (int i = transactions.size() - 1; i >= 0; i--) {

                        if (transactions.get(i).getAmount() > 0) {
                            Transactions t = transactions.get(i);

                            System.out.printf("%-12s %-10s %-15s %-15s %10.2f%n",
                                    t.getDate(),
                                    t.getTime(),
                                    t.getDescription(),
                                    t.getVendor(),
                                    t.getAmount());
                        }

                    }
                    Console.transcationsClose();
                    Console.pressEnterToContinue();
                    break;

                case 'P':
                    Console.paymentMenu();
                    System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                            "Date", "Time", "Description", "Vendor", "Amount");

                    System.out.println("----------------------------------------------------------------");
                    for (int i = transactions.size() - 1; i >= 0; i--) {

                        if (transactions.get(i).getAmount() < 0) {
                            Transactions t = transactions.get(i);

                            System.out.printf("%-12s %-10s %-15s %-15s %10.2f%n",
                                    t.getDate(),
                                    t.getTime(),
                                    t.getDescription(),
                                    t.getVendor(),
                                    t.getAmount());
                        }

                    }
                    Console.transcationsClose();
                    Console.pressEnterToContinue();
                    break;

                case 'R':
                    Reports.reportScreen();
                    break;

                case 'H':
                    Console.showMessage("Returning to home screen...");
                    break;

                default:
                    Console.showMessage("Invalid option. Please try again.");
            }


        }
    }
}
