import java.util.Scanner;

interface Switchable {
    void on();
    void off();

    default void toggle() {
        System.out.println("Toggling device...");
        on();
    }
}

class Fan implements Switchable {
    private String name;
    private boolean isOn;

    public Fan(String name) {
        this.name = name;
        this.isOn = false;
    }

    @Override
    public void on() {
        isOn = true;
        System.out.println(name + " Fan is ON");
    }

    @Override
    public void off() {
        isOn = false;
        System.out.println(name + " Fan is OFF");
    }

    public boolean isOn() {
        return isOn;
    }

    public String getName() {
        return name;
    }
}

class Light implements Switchable {
    private String name;
    private boolean isOn;

    public Light(String name) {
        this.name = name;
        this.isOn = false;
    }

    @Override
    public void on() {
        isOn = true;
        System.out.println(name + " Light is ON");
    }

    @Override
    public void off() {
        isOn = false;
        System.out.println(name + " Light is OFF");
    }

    public boolean isOn() {
        return isOn;
    }

    public String getName() {
        return name;
    }
}

// Functional Interface
@FunctionalInterface
interface SwitchPermission {
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== REMOTE CONTROL =====");

        System.out.print("Enter Fan name: ");
        String fanName = sc.nextLine();

        System.out.print("Enter Light name: ");
        String lightName = sc.nextLine();

        System.out.print("Enter current hour (0-23): ");
        int hour = sc.nextInt();

        if (hour < 0 || hour > 23) {
            System.out.println("Invalid hour.");
            sc.close();
            return;
        }

        Fan fan = new Fan(fanName);
        Light light = new Light(lightName);

        // Array of interface type
        Switchable[] devices = {fan, light};

        System.out.println("\n===== TOGGLING DEVICES =====");

        // Loop over Switchable[]
        for (Switchable device : devices) {
            device.toggle();
        }

        System.out.println("\n===== ANONYMOUS CLASS =====");

        // Functional interface using Anonymous Class
        SwitchPermission permissionAnonymous = new SwitchPermission() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                // Device may switch ON between 6 AM and 10 PM
                return hour >= 6 && hour <= 22;
            }
        };

        if (permissionAnonymous.maySwitchOn(fan, hour)) {
            System.out.println("Anonymous Class: Fan is allowed to switch ON.");
            fan.on();
        } else {
            System.out.println("Anonymous Class: Fan is NOT allowed to switch ON.");
            fan.off();
        }

        System.out.println("\n===== LAMBDA EXPRESSION =====");

        // Functional interface using Lambda
        SwitchPermission permissionLambda =
                (device, currentHour) ->
                        currentHour >= 7 && currentHour <= 21;

        if (permissionLambda.maySwitchOn(light, hour)) {
            System.out.println("Lambda: Light is allowed to switch ON.");
            light.on();
        } else {
            System.out.println("Lambda: Light is NOT allowed to switch ON.");
            light.off();
        }

        System.out.println("\n===== FINAL STATUS =====");

        System.out.println(fanName + " Fan: " +
                (fan.isOn() ? "ON" : "OFF"));

        System.out.println(lightName + " Light: " +
                (light.isOn() ? "ON" : "OFF"));

        sc.close();
    }
}
}
