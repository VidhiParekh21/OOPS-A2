package util;

public class BankResource implements AutoCloseable {

    public void showMessage() {
        System.out.println("Bank resource is being used.");
    }

    @Override
    public void close() {
        System.out.println("Bank resource closed automatically.");
    }
}