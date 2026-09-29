import java.util.Scanner;
public class TollSystem {
    record Vehicle(String number, String type) {}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        int bike = 0, car = 0, truck = 0;
        while (true) {
            System.out.print("Enter Vehicle Number (or done): ");
            String number = sc.nextLine();
            if (number.equalsIgnoreCase("done"))
                break;
            System.out.print("Enter Vehicle Type (bike/car/truck): ");
            String type = sc.nextLine().toLowerCase();
            Vehicle v = new Vehicle(number, type);
            int toll = 0;
            switch (v.type()) {
                case "bike":
                    toll = 20;
                    bike++;
                    break;
                case "car":
                    toll = 50;
                    car++;
                   break;
                case "truck":
                    toll = 150;
                    truck++;
                    break;
                default:
                    System.out.println("Invalid vehicle type");
            }
            total = total + toll;
        }
        System.out.println("Total toll: " + total);

        if (bike >= car && bike >= truck)
            System.out.println("Most frequent: bike");
        else if (car >= bike && car >= truck)
            System.out.println("Most frequent: car");
        else
            System.out.println("Most frequent: truck");
        sc.close();
    }
}
