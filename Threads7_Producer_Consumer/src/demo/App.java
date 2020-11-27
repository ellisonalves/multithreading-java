package demo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    // Thread safe datastruct (F.I.F.O) implemented with arrays
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        App app = new App();

        Thread t1 = new Thread(() -> {
            try {
                app.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                app.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    public void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            // When the queue is full, it waits until items are removed from the queue
            Thread.sleep(200);
            queue.put(random.nextInt(100));
        }
    }

    private void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                // Waits until something is added to the queue and the it will take it and return it
                Integer value = queue.take();

                System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
            }
        }
    }

}
