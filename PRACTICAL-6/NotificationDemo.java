import java.util.Scanner;

@FunctionalInterface
interface Notifier
{
    void send(String message);
}

interface Urgent
{
}

class UrgentSMS implements Notifier, Urgent
{
    public void send(String message)
    {
        System.out.println("SMS [URGENT]: " + message);
    }
}

public class NotificationDemo
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        System.out.print("Do you want to send it as urgent? (yes/no): ");
        String choice = sc.nextLine();

        Notifier email = msg ->
            System.out.println("Email: " + msg);

        Notifier sms = msg ->
            System.out.println("SMS: " + msg);

        Notifier urgentSMS = new UrgentSMS();

        Notifier[] senders;

        if(choice.equalsIgnoreCase("yes"))
        {
            senders = new Notifier[]{email, urgentSMS};
        }
        else
        {
            senders = new Notifier[]{email, sms};
        }

        System.out.println("\nSending notifications:");

        for(Notifier n : senders)
        {
            n.send(message);

            if(n instanceof Urgent)
            {
                n.send(message);
            }
        }

        sc.close();
    }
}

