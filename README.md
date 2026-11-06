# Bank Account Ledger Application

## Overview
The Bank Account Ledger Application is a Java console-based program that allows users to track deposits and payments, store transaction records in a CSV file, and generate financial reports.

## Features
- Add deposits
- Record payments
- Store transactions in `transactions.csv`
- View all transactions
- Filter deposits and payments
- Generate reports:
  - Month-To-Date
  - Previous Month
  - Year-To-Date
  - Previous Year
  - Search By Vendor
- Transactions persist between application sessions

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- ArrayList Collections
- File I/O (BufferedReader and BufferedWriter)
- Java Streams API
- LocalDate and LocalTime

## Project Structure
### Main.java
Entry point of the application.

### Menu.java
Handles navigation, file operations, and transaction management.

### Console.java
Displays menus and gathers user input.

### Transactions.java
Represents a transaction object containing:
- Date
- Time
- Description
- Vendor
- Amount

### Ledger.java
Displays transaction history and ledger information.

### Reports.java
Creates reports based on dates and vendors.

## File Format
Transactions are stored in the following format:

```text
Date|Time|Description|Vendor|Amount
```

Example:

```text
2026-11-05|09:15 AM|Pay|Amazon|1500.00
```

## How It Works
1. Program starts and loads transactions from `transactions.csv`.
2. User selects an option from the home menu.
3. Deposits and payments are saved to memory and the CSV file.
4. Ledger displays transaction history.
5. Reports use Java Streams to filter and sort transactions.
6. Data remains available when the application is restarted.

## Example Reports
### Month-To-Date
Shows transactions from the first day of the current month through today.

### Previous Month
Shows transactions that occurred during the previous month.

### Year-To-Date
Shows all transactions from January 1st of the current year through today.

### Previous Year
Shows all transactions from the previous calendar year.

### Vendor Search
Searches transactions by vendor name using a case-insensitive match.

## Learning Objectives
This project demonstrates:
- Object-oriented design
- Java collections
- File persistence
- Data validation
- Stream filtering and sorting
- Menu-driven application development

## Author
Isael Castillo

## Future Improvements
- Account balance tracking
- Custom date range reports
- Transaction editing and deletion
- Export reports to CSV or PDF
- Graphical User Interface (GUI)
