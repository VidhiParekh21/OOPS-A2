package service;

import model.Account;

public class DeadlockDemo {

    public static void transferWithoutFix(
            Account from,
            Account to,
            long amount) {

        synchronized (from) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " locked " + from.getAccountNumber());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (to) {

                System.out.println(
                        Thread.currentThread().getName()
                        + " locked " + to.getAccountNumber());

                System.out.println(
                        "Transfer: " + amount);
            }
        }
    }

    public static void transferSafely(
            Account from,
            Account to,
            long amount) {

        Account first;
        Account second;

        if (from.getAccountNumber()
                .compareTo(to.getAccountNumber()) < 0) {

            first = from;
            second = to;

        } else {

            first = to;
            second = from;
        }

        synchronized (first) {

            synchronized (second) {

                System.out.println(
                        Thread.currentThread().getName()
                        + " safely transferred "
                        + amount);
            }
        }
    }
}