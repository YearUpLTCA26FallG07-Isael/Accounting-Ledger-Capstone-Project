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

        System.out.println("Welcome to the Bank!\n");

        char command = ' ';

        while(command != 'X'){

            System.out.println("Enter the letter for the option you want.\n");

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
                break;
                case 'P': paymentTransaction(buff ,buffWriter);
                break;
                case 'L': ledger(buff, buffWriter);
                break;

            }
        }

        System.out.println("Thank you for visiting the bank! Have a good day!");

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

        transactions.add(new Transactions(date , time , description , vendor , amount));

        buffWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");

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
            System.out.println("cannot pay positive amounts please try again");
            System.out.print("Enter the amount you have to pay:");
            amount = myScanner.nextDouble();
        }

        transactions.add(new Transactions(date , time , description , vendor , amount));

        buffWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");







    }
    public static void ledger(BufferedReader buff, BufferedWriter buffWriter) throws IOException {
        System.out.println("Welcome to the Ledger menu! \n");
        char command = ' ';
        while(command != 'H'){
            System.out.println("What would you like to view?\n");
            System.out.println("A) View all\n" +
            "\n" +
                    "D) View Deposits\n" +
                    "\n" +
                    "P) View Payments\n" +
                    "\n" +
                    "R) View Report Screen\n" +
                    "\n" +
                    "H) Go back to Home page\n");
            System.out.print("Enter option:");
            command = myScanner.next().toUpperCase().charAt(0);
            myScanner.nextLine();
            switch(command){
                case 'A': for (int i = transactions.size() - 1; i >= 0; i--) {
                    System.out.println(transactions.get(i));
                };
                break;
                case 'D':for (int i = transactions.size() - 1; i >= 0; i--) {
                    if (transactions.get(i).getAmount() > 0) {
                        System.out.println(transactions.get(i));
                    }
                }
                break;
                case 'P':for (int i = transactions.size() - 1; i >= 0; i--) {
                    if (transactions.get(i).getAmount() < 0) {
                        System.out.println(transactions.get(i));
                    }
                }
                break;
                case 'R':reportScreen();
                break;

                case 'H': System.out.println("Returning...\n" + "\n");
                break;

                default:System.out.println("Invalid input please try again\n");
                break;

            }

        }


    }
    public static void reportScreen(){
        System.out.println("Welcome to the report menu! \n");
        int command = 1;
        while(command != 0){
            System.out.println("What would you like to view?\n");
            System.out.println("1) Month to date\n" +
                    "\n" +
                    "2) Previous Month\n" +
                    "\n" +
                    "3) Year to date\n" +
                    "\n" +
                    "4) Previous Year\n" +
                    "\n" +
                    "5) Search by Vendor\n" +
                    "\n" +
                    "0) Back\n");
            System.out.print("Enter option:");
            command = myScanner.nextInt();
            myScanner.nextLine();
            switch(command){

                case 0:System.out.println("Returning...\n" + "\n");
                break;

                case 1: monthToDate(transactions);
                break;

                case 2: previousMonth(transactions);
                break;

                case 3: yearToDate(transactions);
                break;

                case 4: previousYear(transactions);
                break;

                case 5: searchByVendor(transactions , myScanner);
                break;

                default:System.out.println("Invalid input");
                break;


            }

        }


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
                .forEach(System.out::println);
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
                .forEach(System.out::println);
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
                .forEach(System.out::println);
    }
    public static void previousYear(List<Transactions> transactions) {
        int previousYear = LocalDate.now().getYear() - 1;

        transactions.stream()
                .filter(t -> t.getDate().getYear() == previousYear)
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(System.out::println);
    }
    public static void searchByVendor(List<Transactions> transactions, Scanner scanner) {

        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine().toLowerCase();

        transactions.stream()
                .filter(t -> t.getVendor()
                        .toLowerCase()
                        .contains(vendor))
                .sorted(Comparator.comparing(Transactions::getDate)
                        .thenComparing(Transactions::getTime)
                        .reversed())
                .forEach(System.out::println);
    }
 }
