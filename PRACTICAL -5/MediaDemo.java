import java.util.Scanner;

abstract class Media {
    String title;
    int lateDays;

    Media(String title, int lateDays) {
        this.title = title;
        this.lateDays = lateDays;
    }

    abstract double lateFee();
}

class Book extends Media {

    Book(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 2;
    }
}

class DVD extends Media {

    DVD(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 5;
    }
}

class Magazine extends Media {

    Magazine(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 1;
    }
}

public class MediaDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Media[] media = new Media[3];

        // Book
        System.out.println("Enter Book Details");

        System.out.print("Enter title: ");
        String bookTitle = sc.nextLine();

        System.out.print("Enter late days: ");
        int bookDays = sc.nextInt();

        media[0] = new Book(bookTitle, bookDays);

        sc.nextLine();

        // DVD
        System.out.println("\nEnter DVD Details");

        System.out.print("Enter title: ");
        String dvdTitle = sc.nextLine();

        System.out.print("Enter late days: ");
        int dvdDays = sc.nextInt();

        media[1] = new DVD(dvdTitle, dvdDays);

        sc.nextLine();

        // Magazine
        System.out.println("\nEnter Magazine Details");

        System.out.print("Enter title: ");
        String magazineTitle = sc.nextLine();

        System.out.print("Enter late days: ");
        int magazineDays = sc.nextInt();

        media[2] = new Magazine(magazineTitle, magazineDays);

        // Calculate fees
        double total = 0;

        System.out.println("\n--- Late Fee Details ---");

        for (Media m : media) {

            double fee = m.lateFee();

            System.out.println("Title: " + m.title);
            System.out.println("Late Days: " + m.lateDays);
            System.out.println("Late Fee: Rs. " + fee);
            System.out.println();

            total = total + fee;
        }

        System.out.println("Total Late Fee = Rs. " + total);

        sc.close();
    }
}