
package model;

import java.io.Serializable;

public class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    private final TransactionType type;
    private final String accountNumber;
    private final long amount;

    public Command(TransactionType type, String accountNumber, long amount) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public long getAmount() {
        return amount;
    }
}
