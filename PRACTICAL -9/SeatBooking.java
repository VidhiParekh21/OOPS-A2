import java.util.Scanner;
class Cinema {
    private int seatsLeft;
    public Cinema(int seats) {
        seatsLeft = seats;
    }
    public synchronized void book(String customer) {
        if (seatsLeft > 0) {
            System.out.println(
                    customer + " is booking a seat...");
            seatsLeft--;
            System.out.println(
                    customer + " booked successfully.");
        } else {
            System.out.println(
                    customer + " could not book a seat.");
        }
    }
    public int getSeatsLeft() {
        return seatsLeft;
    }
}
public class SeatBooking {
    public static void main(String[] args)
            throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== SEAT BOOKING SYSTEM =====");
        System.out.print("Enter number of seats: ");
        int seats = sc.nextInt();
        System.out.print("Enter number of customers: ");
        int customers = sc.nextInt();
        Cinema cinema = new Cinema(seats);
        Thread[] threads = new Thread[customers];
        System.out.println("\n===== BOOKING STARTED =====");
        for (int i = 0; i < customers; i++) {
           String customer = "Customer-" + (i + 1);
            threads[i] = new Thread(() -> {
                cinema.book(customer);
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
 }
        System.out.println("\n===== FINAL RESULT =====");
        System.out.println(
                "Total seats: " + seats);
        System.out.println(
                "Total customers: " + customers);
        System.out.println(
                "Seats left: " + cinema.getSeatsLeft());
        sc.close();
    }
}