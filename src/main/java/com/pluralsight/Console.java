package com.pluralsight;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    public static char displayHomeScreen() {
        System.out.println("""
==================================================
                BANK ACCOUNT LEDGER
==================================================

    [D] Add Deposit
    [P] Make Payment
    [L] View Ledger
    [X] Exit

==================================================
""");

        System.out.print("Select an option: ");
        return scanner.nextLine().toUpperCase().charAt(0);
    }

    public static char displayLedgerMenu() {
        System.out.println("""
==================================================
                  LEDGER MENU
==================================================

    [A] All Transactions
    [D] Deposits Only
    [P] Payments Only
    [R] Reports
    [H] Home

==================================================
""");

        System.out.print("Enter option: ");
        return scanner.nextLine().toUpperCase().charAt(0);
    }

    public static int displayReportMenu() {
        System.out.println("""
==================================================
                   REPORTS
==================================================

    [1] Month To Date
    [2] Previous Month
    [3] Year To Date
    [4] Previous Year
    [5] Search By Vendor
    [0] Back

==================================================
""");

        System.out.print("Enter option: ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }

    public static String getDescription() {
        System.out.print("Enter description: ");
        return scanner.nextLine();
    }

    public static String getVendor() {
        System.out.print("Enter vendor: ");
        return scanner.nextLine();
    }

    public static double getDepositAmount() {
        double amount;

        do {
            System.out.print("Enter deposit amount: ");
            amount = Double.parseDouble(scanner.nextLine());

            if (amount < 0) {
                System.out.println("Amount cannot be negative.");
            }

        } while (amount < 0);

        return amount;
    }

    public static double getPaymentAmount() {
        double amount;

        do {
            System.out.print("Enter payment amount (negative number): ");
            amount = Double.parseDouble(scanner.nextLine());

            if (amount > 0) {
                System.out.println("Payment must be negative.");
            }

        } while (amount > 0);

        return amount;
    }

    public static String getVendorSearch() {
        System.out.print("Enter vendor name: ");
        return scanner.nextLine();
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void pressEnterToContinue() {
        System.out.println();
        System.out.print("Press Enter to return to the menu...");
        scanner.nextLine();
    }

    public static void transactionsMenu(){
        System.out.println("""
==================================================
                  TRANSACTIONS
==================================================
""");
    }

    public static void transcationsClose(){
        System.out.println("""
==================================================""");
    }

    public static void depositMenu(){
        System.out.println("""
==================================================
                   DEPOSITS
==================================================
""");
    }

    public static void paymentMenu(){
        System.out.println("""
==================================================
                   PAYMENTS
==================================================
""");
    }
}