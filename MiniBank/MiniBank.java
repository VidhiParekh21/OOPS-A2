package service;

import java.util.Scanner;

import model.Account;
import util.BankResource;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Account account1 = new Account("Amit", 5000) {

            @Override
            public double interestRate() {
                return 4.0;
            }

            @Override
            public boolean canWithdraw(long amount) {
                return amount > 0 && amount <= getBalance();
            }
        };

        Account account2 = new Account("Riya", 2000) {

            @Override
            public double interestRate() {
                return 4.0;
            }

            @Override
            public boolean canWithdraw(long amount) {
                return amount > 0 && amount <= getBalance();
            }
        };

        System.out.println("================================");
        System.out.println("          MINI BANK");
        System.out.println("================================");

        System.out.println("\n===== ACCOUNT DETAILS =====");

        System.out.println("Account 1:");
        System.out.println(account1);

        System.out.println("\nAccount 2:");
        System.out.println(account2);

        // DEPOSIT
        try {

            System.out.print(
                    "\nEnter amount to deposit in Account 1: ");

            long amount = sc.nextLong();

            account1.deposit(amount);

            System.out.println(
                    "Deposit successful.");

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Deposit failed: " + e.getMessage());

        } finally {

            System.out.println(
                    "Deposit operation completed.");
        }

        // WITHDRAW
        try {

            System.out.print(
                    "\nEnter amount to withdraw from Account 1: ");

            long amount = sc.nextLong();

            account1.withdraw(amount);

            System.out.println(
                    "Withdrawal successful.");

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Withdrawal failed: "
                    + e.getMessage());

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Withdrawal failed: "
                    + e.getMessage());

            System.out.println(
                    "Shortfall: "
                    + e.getShortfall());

        } finally {

            System.out.println(
                    "Withdrawal operation completed.");
        }

        // TRANSFER
        try {

            System.out.print(
                    "\nEnter amount to transfer "
                    + "from Account 1 to Account 2: ");

            long amount = sc.nextLong();

            account1.transfer(account2, amount);

        } catch (BankException e) {

            System.out.println(
                    "Transfer failed: "
                    + e.getMessage());

        } finally {

            System.out.println(
                    "Transfer operation completed.");
        }

        // TRY-WITH-RESOURCES
        System.out.println("\n===== BANK RESOURCE =====");

        try (BankResource resource =
                     new BankResource()) {

            resource.showMessage();

        } catch (Exception e) {

            System.out.println(
                    "Resource error: "
                    + e.getMessage());
        }

        // FINAL BALANCES
        System.out.println("\n===== FINAL BALANCES =====");

        System.out.println(
                "Account 1 Balance: "
                + account1.getBalance());

        System.out.println(
                "Account 2 Balance: "
                + account2.getBalance());

        System.out.println(
                "\n================================");

        System.out.println(
                "       MINI BANK FINISHED");

        System.out.println(
                "================================");

        sc.close();
    }
}