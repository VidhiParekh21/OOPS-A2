
package service;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import model.Account;
import model.SavingsAccount;

public class MiniBank {
    private static final String DATA_FILE = "accounts.txt";

    public static void main(String[] args) {
        Repository<Account> repository = new Repository<>();
        BankService bank = new BankService(repository);
        loadAccounts(repository, bank);

        try (Scanner sc = new Scanner(System.in)) {
            int choice = 0;

            do {
                System.out.println("\n================================");
                System.out.println("          MINI BANK");
                System.out.println("================================");
                System.out.println("1. Open Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Account Statement");
                System.out.println("6. List All Accounts");
                System.out.println("7. Process Batch Deposits");
                System.out.println("8. Transaction Report");
                System.out.println("9. Save and Exit");
                System.out.print("Enter choice: ");

                try {
                    choice = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid menu number.");
                    continue;
                }

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Owner name: ");
                            String name = sc.nextLine().trim();
                            System.out.print("Initial balance: ");
                            long opening = Long.parseLong(sc.nextLine().trim());
                            Account created = bank.openAccount(name, opening);
                            System.out.println("Account created successfully!");
                            System.out.println(created);
                            break;

                        case 2:
                            System.out.print("Account number: ");
                            String depositNo = sc.nextLine().trim();
                            System.out.print("Amount: ");
                            long depositAmount = Long.parseLong(sc.nextLine().trim());
                            bank.deposit(depositNo, depositAmount);
                            System.out.println("Deposit successful.");
                            break;

                        case 3:
                            System.out.print("Account number: ");
                            String withdrawNo = sc.nextLine().trim();
                            System.out.print("Amount: ");
                            long withdrawAmount = Long.parseLong(sc.nextLine().trim());
                            bank.withdraw(withdrawNo, withdrawAmount);
                            System.out.println("Withdrawal successful.");
                            break;

                        case 4:
                            System.out.print("From account: ");
                            String from = sc.nextLine().trim();
                            System.out.print("To account: ");
                            String to = sc.nextLine().trim();
                            System.out.print("Amount: ");
                            long transferAmount = Long.parseLong(sc.nextLine().trim());
                            bank.transfer(from, to, transferAmount);
                            System.out.println("Transfer successful.");
                            break;

                        case 5:
                            System.out.print("Account number: ");
                            String statementNo = sc.nextLine().trim();
                            System.out.println(bank.statement(statementNo));
                            break;

                        case 6:
                            printAccounts(bank.listAccounts());
                            break;

                        case 7:
                            processBatch(bank);
                            break;

                        case 8:
                            printReport(bank);
                            break;

                        case 9:
                            saveAccounts(bank.listAccounts());
                            System.out.println("Accounts saved. Goodbye!");
                            break;

                        default:
                            System.out.println("Invalid menu choice.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                } catch (InvalidAmountException | InsufficientFundsException e) {
                    System.out.println("Transaction failed: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Operation failed: " + e.getMessage());
                }
            } while (choice != 9);
        }
    }

    private static void printAccounts(ArrayList<Account> accounts) {
        System.out.println("\n===== ALL ACCOUNTS =====");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private static void processBatch(BankService bank) {
        ArrayList<Account> accounts = bank.listAccounts();

        if (accounts.isEmpty()) {
            System.out.println("Open an account before processing a batch.");
            return;
        }

        TransactionProcessor processor = new TransactionProcessor();

        for (Account account : accounts) {
            processor.addAccount(account);
        }

        for (Account account : accounts) {
            processor.submit(() -> {
                try {
                    bank.deposit(account.getAccountNumber(), 100);
                    bank.record("Batch deposit of 100 completed for "
                            + account.getAccountNumber());
                    System.out.println(Thread.currentThread().getName()
                            + " deposited 100 into "
                            + account.getAccountNumber());
                } catch (Exception e) {
                    bank.record("Batch deposit failed for "
                            + account.getAccountNumber() + ": "
                            + e.getMessage());
                }
            });
        }

        processor.stop();
        System.out.println("Batch processing completed.");
    }

    private static void printReport(BankService bank) {
        System.out.println("\n===== TRANSACTION REPORT =====");
        ArrayList<String> history = bank.getHistory();

        if (history.isEmpty()) {
            System.out.println("No transactions recorded this session.");
        } else {
            for (String item : history) {
                System.out.println(item);
            }
        }

        System.out.println("\n===== ACCOUNT SUMMARY =====");
        printAccounts(bank.listAccounts());
    }

    private static void loadAccounts(Repository<Account> repository,
            BankService bank) {
        File file = new File(DATA_FILE);

        if (!file.exists()) {
            System.out.println("No saved accounts found. Starting new bank.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 3);

                if (parts.length != 3) {
                    continue;
                }

                String number = parts[0];
                String name = parts[1];
                long balance = Long.parseLong(parts[2]);

                Account account = new SavingsAccount(number, name, balance);
                repository.save(account);
                bank.record("Loaded account " + number);
            }

            System.out.println("Saved accounts loaded.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load accounts: " + e.getMessage());
        }
    }

    private static void saveAccounts(ArrayList<Account> accounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Account account : accounts) {
                writer.println(account.getAccountNumber() + "|"
                        + account.getOwnerName() + "|"
                        + account.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Could not save accounts: " + e.getMessage());
        }
    }
}
