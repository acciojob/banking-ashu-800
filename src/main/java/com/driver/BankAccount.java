package com.driver;

import java.sql.SQLOutput;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount() {
    }

    public BankAccount(String name, double balance, double minBalance) {
            this.name=name;
            this.balance=balance;
            this.minBalance=minBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        int total=0;
        while(digits>0){
            int temp=digits%10;
            total+=temp;
            try {
                if (temp < 0 || temp > 9) {
                    throw new RuntimeException("Account Number can not be generated");
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
            digits=digits/10;
        }

        try {
            if (sum!=total) {
                throw new RuntimeException("Account Number can not be generated");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
            this.balance-=amount;
            try{
                if(this.balance < this.minBalance){
                    throw new RuntimeException("Insufficient Balance");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
    }

}