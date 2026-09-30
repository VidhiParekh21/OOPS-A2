package discount;

import java.util.Scanner;

public class DiscountEngine {

    @FunctionalInterface
    interface DiscountRule {
        double apply(double price);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of prices: ");
        int n = sc.nextInt();

        double[] prices = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter price " + (i + 1) + ": ");
            prices[i] = sc.nextDouble();
        }

        System.out.println("\nChoose Discount Rule:");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Rs.100 Discount");
        System.out.println("4. No Discount");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        if (choice == 1) {
            rule = price -> price - (price * 0.10);
        } 
        else if (choice == 2) {
            rule = price -> price - (price * 0.20);
        } 
        else if (choice == 3) {
            rule = price -> Math.max(0, price - 100);
        } 
        else {
            rule = price -> price;
        }

        System.out.println("\nPrices after discount:");

        for (double price : prices) {

            double finalPrice = rule.apply(price);

            System.out.println(
                "Original Price: " + price +
                "  Final Price: " + finalPrice
            );
        }

        sc.close();
    }
}