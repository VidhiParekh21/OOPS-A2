import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Scanner;

// Run annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

// Class containing test methods
class MyTests {

    @Run
    public void additionTest() {
        int a = 10;
        int b = 20;
        System.out.println("Addition Test: " + (a + b));
    }

    @Run
    public void stringTest() {
        String name = "Java";
        System.out.println("String Test: " + name);
    }

    public void normalMethod() {
        System.out.println("This method should NOT run.");
    }

    @Run
    public void multiplicationTest() {
        int a = 5;
        int b = 4;
        System.out.println("Multiplication Test: " + (a * b));
    }
}

// Main test runner
public class TestRunner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== MINI TEST RUNNER =====");

        System.out.print("Enter test class name (MyTests): ");
        String className = sc.nextLine();

        try {
            Class<?> testClass = Class.forName(className);

            Object testObject = testClass.getDeclaredConstructor().newInstance();

            Method[] methods = testClass.getDeclaredMethods();

            int count = 0;

            System.out.println("\n===== RUNNING TESTS =====");

            for (Method method : methods) {

                // Check for @Run annotation
                if (method.isAnnotationPresent(Run.class)) {

                    // Only run no-argument methods
                    if (method.getParameterCount() == 0) {

                        System.out.println("\nRunning: "
                                + method.getName());

                        method.invoke(testObject);

                        count++;
                    }
                }
            }

            System.out.println("\n===== RESULT =====");
            System.out.println("Total @Run methods executed: " + count);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}