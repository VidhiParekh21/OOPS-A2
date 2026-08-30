public class StatementFormatter {

    public static String buildStatement(Account account) {

        StringBuilder statement = new StringBuilder();

        statement.append("----- ACCOUNT STATEMENT -----\n");
        statement.append("Account Number: ");
        statement.append(account.getAccountNumber());
        statement.append("\n");

        statement.append("Owner Name: ");
        statement.append(account.getOwnerName());
        statement.append("\n");

        statement.append("Balance: ");
        statement.append(account.getBalance());
        statement.append("\n");

        statement.append("-----------------------------");

        return statement.toString();
    }
}