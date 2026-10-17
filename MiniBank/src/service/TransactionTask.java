package service;

import model.Account;

public class TransactionTask implements Runnable {

    private Account account;
    private boolean deposit;
    private long amount;

    public TransactionTask(Account account, boolean deposit, long amount) {
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }

    @Override
    public void run() {

        try {

            if (deposit) {
                account.deposit(amount);

                System.out.println(
                        Thread.currentThread().getName()
                        + " deposited " + amount);

            } else {

                account.withdraw(amount);

                System.out.println(
                        Thread.currentThread().getName()
                        + " withdrew " + amount);
            }

        } catch (Exception e) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " failed: " + e.getMessage());
        }
    }
}
