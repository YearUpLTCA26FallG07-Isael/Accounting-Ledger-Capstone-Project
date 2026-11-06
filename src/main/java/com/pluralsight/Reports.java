package com.pluralsight;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static com.pluralsight.Menu.transactions;

public class Reports {
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

        System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------");

        transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstDay)
                        && !t.getDate().isAfter(today))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> System.out.printf(
                        "%-12s %-10s %-15s %-15s %10.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount()));
        Console.pressEnterToContinue();
    }

    public static void previousMonth(List<Transactions> transactions) {
        LocalDate today = LocalDate.now();

        LocalDate firstDayPrevMonth =
                today.minusMonths(1).withDayOfMonth(1);

        LocalDate lastDayPrevMonth =
                firstDayPrevMonth.withDayOfMonth(
                        firstDayPrevMonth.lengthOfMonth());

        System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------");

        transactions.stream().filter(t -> !t.getDate().isBefore(firstDayPrevMonth)
                        && !t.getDate().isAfter(lastDayPrevMonth))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> System.out.printf(
                        "%-12s %-10s %-15s %-15s %10.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount()));
        Console.pressEnterToContinue();
    }

    public static void yearToDate(List<Transactions> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayYear = today.withDayOfYear(1);

        System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------");

        transactions.stream()
                .filter(t -> !t.getDate().isBefore(firstDayYear)
                        && !t.getDate().isAfter(today))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> System.out.printf(
                        "%-12s %-10s %-15s %-15s %10.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount()));
        Console.pressEnterToContinue();
    }

    public static void previousYear(List<Transactions> transactions) {
        int previousYear = LocalDate.now().getYear() - 1;

        System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------");

        transactions.stream()
                .filter(t -> t.getDate().getYear() == previousYear)
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> System.out.printf(
                        "%-12s %-10s %-15s %-15s %10.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount()));
        Console.pressEnterToContinue();
    }

    public static void searchByVendor(List<Transactions> transactions, String vendor) {
        System.out.printf("%-12s %-10s %-15s %-15s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("----------------------------------------------------------------");

        transactions.stream()
                .filter(t -> t.getVendor()
                        .toLowerCase()
                        .contains(vendor.toLowerCase()))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(t -> System.out.printf(
                        "%-12s %-10s %-15s %-15s %10.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount()));
        Console.pressEnterToContinue();
    }

}
