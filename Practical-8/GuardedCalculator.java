import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean success = false;

        while (!success) {

            try {
                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                char op = sc.nextLine().charAt(0);

                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine());

                double result;

                if (op == '/') {
                    if (b == 0) {
                        throw new DivideByZeroException(
                                "Cannot divide by zero.");
                    }
                    result = a / b;
                } else if (op == '+') {
                    result = a + b;
                } else if (op == '-') {
                    result = a - b;
                } else if (op == '*') {
                    result = a * b;
                } else {
                    System.out.println("Invalid operator.");
                    continue;
                }

                System.out.println("Result = " + result);
                success = true;

            } catch (NumberFormatException e) {

                System.out.println("Invalid number input.");

            } catch (DivideByZeroException e) {

                System.out.println(e.getMessage());

            } finally {

                System.out.println("Calculation attempt completed.");
                System.out.println();
            }
        }

        sc.close();
    }
}
