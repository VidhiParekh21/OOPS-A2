package service;

public class TransactionBuffer {

    private int[] buffer;
    private int count = 0;

    public TransactionBuffer(int size) {
        buffer = new int[size];
    }

    public synchronized void add(int transaction) {

        while (count == buffer.length) {

            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        buffer[count] = transaction;
        count++;

        System.out.println(
                "Producer added transaction: " + transaction);

        notify();
    }

    public synchronized int remove() {

        while (count == 0) {

            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return -1;
            }
        }

        int transaction = buffer[0];

        for (int i = 1; i < count; i++) {
            buffer[i - 1] = buffer[i];
        }

        count--;

        System.out.println(
                "Consumer processed transaction: "
                + transaction);

        notify();

        return transaction;
    }
}