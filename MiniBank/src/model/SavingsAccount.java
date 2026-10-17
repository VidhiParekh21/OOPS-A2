package model;
import java.util.concurrent.atomic.AtomicInteger;
public class SavingsAccount extends Account {
private static final long MIN_BALANCE = 1000;
private static final double RATE = 0.04;
private static final AtomicInteger COUNT = new AtomicInteger(1);
public SavingsAccount(String ownerName, long balance) {
this(String.format("AC%04d", COUNT.getAndIncrement()),
ownerName, balance);
}
public SavingsAccount(String accountNumber, String ownerName,
long balance) {
super(accountNumber, ownerName, balance);
}
@Override
public double interestRate() {
return RATE;
}
@Override
public boolean canWithdraw(long amount) {
return amount > 0
&& getBalance() - amount >= MIN_BALANCE;
}
}