class Counter {
   int count = 0;
    public void increment() {
        count++;
    }
}
public class CounterRace {
    public static void main(String[] args)
            throws InterruptedException {
        Counter counter = new Counter();
        int numberOfThreads = 10;
        int incrementsPerThread = 100000;
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j=0; j<incrementsPerThread; j++) {
                counter.increment();
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        int expected =
                numberOfThreads * incrementsPerThread;
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count: " + counter.count);
    }
}
