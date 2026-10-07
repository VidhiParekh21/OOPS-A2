import java.util.Scanner;

class Total {
    long sum = 0;
    public void add(long value) {
        sum += value;
    }
}
public class SharedTotal {

    public static void main(String[] args)
            throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("===============================");
        System.out.println("       SHARED TOTAL PROGRAM");
        System.out.println("============================");
        System.out.print("Enter size of array: ");
        int size = sc.nextInt();
        int[] numbers = new int[size];
        System.out.println("\nEnter " + size + " numbers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = sc.nextInt();
        }
        System.out.print("\nEnter number of threads: ");
        int numberOfThreads = sc.nextInt();
        if (numberOfThreads > size) {
            numberOfThreads = size;
        }
        Total total = new Total();
        Thread[] threads = new Thread[numberOfThreads];
        int partSize = size / numberOfThreads;
        System.out.println("\n================================");
        System.out.println("       PROCESSING STARTED");
        System.out.println("================================");
        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * partSize;
            int end;
            if (i == numberOfThreads - 1) {
                end = size;
            } else {
                end = start + partSize;
            }
            final int threadNumber = i + 1;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    total.add(numbers[j]);
                }
                System.out.println(
                        "Thread " + threadNumber
                        + " processed from index "
                        + start + " to " + (end - 1));
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long expectedTotal = 0;
        for (int number : numbers) {
            expectedTotal += number;
        }
        System.out.println("\n================================");
        System.out.println("             RESULT");
        System.out.println("================================");
        System.out.println(
                "Expected total: " + expectedTotal);
        System.out.println(
                "Actual total  : " + total.sum);
        if (total.sum == expectedTotal) {
            System.out.println("Result is correct.");
        } else {
            System.out.println("Race condition detected!");
            System.out.println("Some additions were lost.");
        }
        System.out.println("================================");
        sc.close();
        }
}