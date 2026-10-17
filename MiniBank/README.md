\# MiniBank – Java Banking Application



\## 1. Project Overview



MiniBank is a console-based banking application developed using Java. It demonstrates object-oriented programming, exception handling, generics, annotations, file handling, and multithreading.



The application allows users to create bank accounts, perform transactions, view account details, and save account information for future use.



\## 2. Objectives



\- Implement object-oriented programming concepts.

\- Use classes, interfaces, inheritance, and abstract classes.

\- Handle invalid inputs and transaction errors using exceptions.

\- Store and retrieve account details using a generic repository.

\- Apply annotations and reflection for validation.

\- Demonstrate multithreading using a thread pool.

\- Save and load account details using file handling.

\- Build and execute a runnable JAR file.



\## 3. Technologies Used



\- Java (JDK 17 or later)

\- Java Collections Framework

\- Generics and annotations

\- Exception handling

\- File handling

\- Multithreading and concurrency

\- Windows PowerShell



\## 4. Project Structure



```text

Mini Bank/

├── src/

│   ├── exception/

│   │   ├── InsufficientFundsException.java

│   │   └── InvalidAmountException.java

│   ├── model/

│   │   ├── Account.java

│   │   ├── Customer.java

│   │   ├── SavingsAccount.java

│   │   ├── Command.java

│   │   ├── Transactable.java

│   │   ├── InterestBearing.java

│   │   ├── WithdrawRule.java

│   │   ├── Premium.java

│   │   └── annotation/

│   ├── service/

│   │   ├── MiniBank.java

│   │   ├── BankService.java

│   │   ├── Repository.java

│   │   ├── AccountStore.java

│   │   ├── TransactionProcessor.java

│   │   ├── TransactionTask.java

│   │   ├── TransactionBuffer.java

│   │   ├── AccountWorker.java

│   │   └── DeadlockDemo.java

│   └── util/

│       ├── Validator.java

│       ├── AnnotationValidator.java

│       ├── BankResource.java

│       ├── CommandParser.java

│       └── StatementFormatter.java

├── out/

├── MiniBank.jar

├── accounts.txt

└── README.md

```



The `out/` directory contains compiled Java class files. The `accounts.txt` file is created when account data is saved.



\## 5. Features



1\. \*\*Open Account:\*\* Create a new savings account.

2\. \*\*Deposit:\*\* Add money to an existing account.

3\. \*\*Withdraw:\*\* Withdraw money while maintaining the minimum balance.

4\. \*\*Transfer:\*\* Transfer money between two different accounts.

5\. \*\*Account Statement:\*\* Display account details and balance.

6\. \*\*List Accounts:\*\* Display all available accounts.

7\. \*\*Batch Processing:\*\* Process deposits using a thread pool.

8\. \*\*Transaction Report:\*\* Display recorded transactions and account summaries.

9\. \*\*File Persistence:\*\* Save and load account details using `accounts.txt`.

10\. \*\*Validation:\*\* Validate inputs and handle invalid transactions.

11\. \*\*Concurrency:\*\* Use synchronization and a thread pool for safer transaction processing.



\## 6. How to Compile and Run



Open PowerShell in the project directory.



\### Compile the source files



```powershell

$files = Get-ChildItem .\\src -Recurse -Filter \*.java | ForEach-Object { $\_.FullName }

New-Item -ItemType Directory -Path out -Force

javac -d out $files

```



\### Run without a JAR



```powershell

java -cp out service.MiniBank

```



\### Build the runnable JAR



```powershell

jar cfe MiniBank.jar service.MiniBank -C out .

```



\### Run the JAR



```powershell

java -jar MiniBank.jar

```



\## 7. Sample Usage



```text

================================

&#x20;         MINI BANK

================================

1\. Open Account

2\. Deposit

3\. Withdraw

4\. Transfer

5\. Account Statement

6\. List All Accounts

7\. Process Batch Deposits

8\. Transaction Report

9\. Save and Exit

Enter choice:

```



Select an option and follow the prompts. Enter `9` to save the account details and exit.



\## 8. Data Storage



Account details are stored in `accounts.txt` in the following format:



```text

AC0001|Amit|5000

AC0002|Riya|2000

```



Each line contains the account number, account holder's name, and balance, separated by the `|` character.



The application loads this file when it starts and saves account details when the user selects Save and Exit.



\## 9. Error Handling and Validation



The application handles invalid numeric inputs, invalid transaction amounts, missing accounts, and insufficient funds. Custom exceptions are used for invalid amounts and insufficient funds.



Withdrawals and transfers must respect the minimum account balance of ₹1,000.



\## 10. Concepts Demonstrated



\- Classes and objects

\- Encapsulation and inheritance

\- Interfaces and polymorphism

\- Abstract classes and enums

\- Generics and collections

\- Annotations and reflection

\- Custom exception handling

\- File input and output

\- Multithreading and synchronization

\- ExecutorService and thread pools

\- Runnable JAR creation



\## 11. Limitations



\- The application uses a console-based interface.

\- Account information is stored in a local text file rather than a database.

\- Transaction history is maintained in memory and is not permanently stored.

\- This is an educational project and is not intended for real banking transactions.



\## 12. Conclusion



MiniBank demonstrates how Java programming concepts can be combined to develop a simple banking application. It provides account management, transaction processing, validation, file persistence, and multithreading. The runnable JAR makes it possible to execute the application without manually compiling the source files each time.



