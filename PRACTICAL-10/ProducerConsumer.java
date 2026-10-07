import java.util.LinkedList;
import java.util.Queue;

class Buffer {

    Queue<Integer> queue = new LinkedList<>();
    int size = 3;

    synchronized void produce(int number) throws Exception {

        while (queue.size() == size) {
            wait();
        }

        queue.add(number);

        System.out.println("Produced: " + number);

        notify();
    }

    synchronized int consume() throws Exception {

        while (queue.isEmpty()) {
            wait();
        }

        int number = queue.remove();

        System.out.println("Consumed: " + number);

        notify();

        return number;
    }
}

public class ProducerConsumer {

    public static void main(String[] args) throws Exception {

        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(200);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        });

        Thread consumer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {
                    buffer.consume();
                    Thread.sleep(300);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items produced and consumed.");
    }
}