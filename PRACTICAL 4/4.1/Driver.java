import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.println();

        System.out.println("Length >= 8: "
                + PasswordChecker.hasLength(password));

        System.out.println("Contains uppercase: "
                + PasswordChecker.hasUppercase(password));

        System.out.println("Contains digit: "
                + PasswordChecker.hasDigit(password));

        System.out.println("Contains special character: "
                + PasswordChecker.hasSpecial(password));

        System.out.println("Password Strength: "
                + PasswordChecker.strength(password));

        sc.close();
    }
}
