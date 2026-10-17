package service;

import java.util.concurrent.*;
import model.Account;

public class TransactionProcessor {
    private final ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();

    private final ExecutorService executor =
            Executors.newFixedThreadPool(4);

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return new ConcurrentHashMap<>(accounts);
    }

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void stop() {
        executor.shutdown();

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}