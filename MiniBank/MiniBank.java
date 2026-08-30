import java.util.Scanner;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter correct mobile number: ");
        String mobile1 = sc.nextLine();

        System.out.print("Enter wrong mobile number: ");
        String mobile2 = sc.nextLine();

        System.out.println("Correct mobile: "
                + Validator.isValidMobile(mobile1));

        System.out.println("Wrong mobile: "
                + Validator.isValidMobile(mobile2));

        System.out.println();

        System.out.print("Enter correct email: ");
        String email1 = sc.nextLine();

        System.out.print("Enter wrong email: ");
        String email2 = sc.nextLine();

        System.out.println("Correct email: "
                + Validator.isValidEmail(email1));

        System.out.println("Wrong email: "
                + Validator.isValidEmail(email2));

        System.out.println();

        System.out.print("Enter correct PAN: ");
        String pan1 = sc.nextLine();

        System.out.print("Enter wrong PAN: ");
        String pan2 = sc.nextLine();

        System.out.println("Correct PAN: "
                + Validator.isValidPan(pan1));

        System.out.println("Wrong PAN: "
                + Validator.isValidPan(pan2));

        System.out.println();

        System.out.print("Enter correct IFSC: ");
        String ifsc1 = sc.nextLine();

        System.out.print("Enter wrong IFSC: ");
        String ifsc2 = sc.nextLine();

        System.out.println("Correct IFSC: "
                + Validator.isValidIfsc(ifsc1));

        System.out.println("Wrong IFSC: "
                + Validator.isValidIfsc(ifsc2));

        System.out.println();

        System.out.print("Enter command: ");
        String line = sc.nextLine();

        try {
            Command command = CommandParser.parse(line);

            System.out.println("Transaction Type: "
                    + command.type());

            System.out.println("Account Number: "
                    + command.accountNumber());

            System.out.println("Amount: "
                    + command.amount());

        } catch (Exception e) {
            System.out.println("Invalid command.");
        }

        sc.close();
    }
}
