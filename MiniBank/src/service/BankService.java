
package service;

import model.Account;
import model.SavingsAccount;
import exception.InvalidAmountException;
import exception.InsufficientFundsException;
import util.AnnotationValidator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BankService {
    private final Repository<Account> repository;
    private final List<String> history =
            Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger NEXT_ACCOUNT =
            new AtomicInteger(1);

    public BankService(Repository<Account> repository) {
        this.repository = repository;
    }

    public synchronized Account openAccount(String name, long balance)
            throws InvalidAmountException {
        if (name == null || name.trim().isEmpty()
                || name.contains("|")) {
            throw new IllegalArgumentException("Invalid owner name");
        }
        if (balance < 0) {
            throw new InvalidAmountException(
                    "Initial balance cannot be negative");
        }

        String number;
        do {
            number = String.format("AC%04d",
                    NEXT_ACCOUNT.getAndIncrement());
        } while (repository.findById(number) != null);

        Account account = new SavingsAccount(number, name.trim(), balance);
        String[] errors = AnnotationValidator.validate(account);
        if (errors.length > 0) {
            throw new IllegalArgumentException(
                    String.join(", ", errors));
        }

        repository.save(account);
        history.add("Opened " + number + " for " + name);
        return account;
    }

    public synchronized void deposit(String number, long amount)
            throws InvalidAmountException {
        Account account = getAccount(number);
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit must be greater than zero");
        }
        account.deposit(amount);
        history.add("Deposited " + amount + " into " + number);
    }

    public synchronized void withdraw(String number, long amount)
            throws InvalidAmountException, InsufficientFundsException {
        Account account = getAccount(number);
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal must be greater than zero");
        }
        if (!account.withdraw(amount)) {
            throw new InsufficientFundsException(
                    "Withdrawal failed. Minimum balance is 1000");
        }
        history.add("Withdrew " + amount + " from " + number);
    }

    public void transfer(String fromNumber, String toNumber, long amount)
            throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Transfer must be greater than zero");
        }

        Account from = getAccount(fromNumber);
        Account to = getAccount(toNumber);
        if (from == to) {
            throw new IllegalArgumentException(
                    "Choose two different accounts");
        }

        Account first = from.compareTo(to) < 0 ? from : to;
        Account second = from.compareTo(to) < 0 ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (!from.withdraw(amount)) {
                    throw new InsufficientFundsException(
                            "Insufficient balance for transfer");
                }
                to.deposit(amount);
            }
        }

        history.add("Transferred " + amount + " from "
                + fromNumber + " to " + toNumber);
    }

    public String statement(String number) {
        return getAccount(number).toString();
    }

    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    public ArrayList<String> getHistory() {
        synchronized (history) {
            return new ArrayList<>(history);
        }
    }

    public void record(String message) {
        history.add(message);
    }

    private Account getAccount(String number) {
        Account account = repository.findById(number);
        if (account == null) {
            throw new IllegalArgumentException(
                    "Account not found: " + number);
        }
        return account;
    }
}
