import java.util.Scanner;

class MyResource implements AutoCloseable {

    public MyResource() {
        System.out.println("Resource opened.");
    }

    public void use(boolean causeError) throws Exception {

        System.out.println("Using resource...");

        if (causeError) {
            throw new Exception("Error while using resource.");
        }

        System.out.println("Resource used successfully.");
    }

    @Override
    public void close() {
        System.out.println("Resource closed automatically.");
    }
}

public class ResourceDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== AutoCloseable Resource Demo =====");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Hello, " + name + "!");

        System.out.print("Do you want to cause an exception? (yes/no): ");
        String choice = sc.nextLine();

        boolean causeError = choice.equalsIgnoreCase("yes");

        try (MyResource resource = new MyResource()) {

            System.out.println("Resource is being used by " + name + ".");

            resource.use(causeError);

        } catch (Exception e) {

            System.out.println("Exception caught: " + e.getMessage());

        } finally {

            System.out.println("Program execution completed.");
        }

        sc.close();
    }
}
