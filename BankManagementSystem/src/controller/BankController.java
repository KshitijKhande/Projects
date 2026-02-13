package controller;

import model.*;
import view.BankView;

public class BankController {
    BankModel model;
    BankView view;

    public BankController(BankModel model, BankView view) {
        this.model = model;
        this.view = view;
    }

    // Create a new account
    public void createAccount(Account account) {
        model.addAccount(account);
        view.showMessage("Account created: " + account.getAccNo() + " (" + account.getName() + ")");
    }

    // Deposit
    public void deposit(double accNo, double amount) {
        Account acc = model.getAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
            model.addTransaction(new Transaction(amount, "DEPOSIT", accNo));
            view.showMessage("Deposited ₹" + amount + " to account " + accNo);
        } else {
            view.showError("Account not found.");
        }
    }

    // Withdraw
    public void withdraw(double accNo, double amount) {
        Account acc = model.getAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
            model.addTransaction(new Transaction(amount, "WITHDRAW", accNo));
            view.showMessage("Withdrew ₹" + amount + " from account " + accNo);
        } else {
            view.showError("Account not found.");
        }
    }

    // Interest calculation
    public void calculateInterest(double accNo) {
        Account acc = model.getAccount(accNo);
        if (acc != null) {
            acc.calculateInterest();
            view.showMessage("Interest calculated for account " + accNo);
        } else {
            view.showError("Account not found.");
        }
    }

    // Display all accounts
    public void displayAccounts() {
        view.displayAccounts(model.accounts, model.accountCount);
    }

    // Display all transactions
    public void displayTransactions() {
        view.displayTransactions(model.transactions, model.transactionCount);
    }

    // End-of-day report 
    public void endOfDayReport() {
        view.endOfDayReport(model.transactions, model.transactionCount);
    }
    
    public void displayAccountsByType(String type) {
        Account[] accounts = model.getAccounts();
        int count = model.getAccountCount();
        System.out.println("=== " + type + " Accounts ===");
        for (int i = 0; i < count; i++) {
            if (accounts[i].getClass().getSimpleName().toUpperCase().contains(type)) {
                view.showMessage(accounts[i].toString());
            }
        }
    }

}

