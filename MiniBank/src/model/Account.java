
package model;

import model.annotation.Id;

public class Account implements Comparable<Account>{
    @Id
    private final String accountNumber;
    private final String ownerName;
    private long balance;

    public Account(String accountNumber,String ownerName,long balance){
        this.accountNumber=accountNumber;
        this.ownerName=ownerName;
        this.balance=balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public synchronized long getBalance(){
        return balance;
    }

    public synchronized void deposit(long amount){
        if(amount>0){
            balance+=amount;
        }
    }

    public boolean canWithdraw(long amount){
        return amount>0&&getBalance()-amount>=1000;
    }

    public synchronized boolean withdraw(long amount){
        if(!canWithdraw(amount)){
            return false;
        }
        balance-=amount;
        return true;
    }

    public double interestRate(){
        return 0.0;
    }

    @Override
    public int compareTo(Account other){
        return accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString(){
        return accountNumber+" | "+ownerName+" | Balance: "+getBalance();
    }
}
