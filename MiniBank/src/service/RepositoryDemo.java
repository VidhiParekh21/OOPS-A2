
package service;

import model.Account;
import model.SavingsAccount;

public class RepositoryDemo{
    public static void main(String[] args){
        Repository<Account> repo=new Repository<>();

        Account a1=new SavingsAccount("AC0001","Amit",5000);
        Account a2=new SavingsAccount("AC0002","Riya",2000);
        Account a3=new SavingsAccount("AC0003","Neha",3000);

        repo.saveAll(a1,a2,a3);

        System.out.println("===== ACCOUNT SEARCH =====");
        System.out.println(repo.findById("AC0001"));

        System.out.println("\n===== ALL ACCOUNTS =====");
        for(Account a:repo.findAll()){
            System.out.println(a);
        }

        System.out.println("\n===== DELETE ACCOUNT =====");
        System.out.println("Deleted: "+repo.delete("AC0003"));

        Integer[] numbers={10,20,30};
        System.out.println("\n===== AVERAGE =====");
        System.out.println(Repository.average(numbers));
    }
}
