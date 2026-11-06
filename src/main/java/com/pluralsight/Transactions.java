package com.pluralsight;

import java.time.LocalDate;

public class Transactions {
    private LocalDate date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transactions(LocalDate date , String time , String description , String vendor , double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Transactions(String line) {
        String[] parts = line.split("\\|");
        if(parts.length != 5){
            throw new IllegalArgumentException(
                    "Invalid transaction record");
        }
        this.date = LocalDate.parse(parts[0]);
        this.time = parts[1];
        this.description = parts[2];
        this.vendor = parts[3];
        this.amount = Double.parseDouble(parts[4]);
    }

    public Transactions() {

    }

    public String toString(){
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;

    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
