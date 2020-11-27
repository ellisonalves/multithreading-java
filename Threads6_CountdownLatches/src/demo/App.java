package demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(String.format("Started %s", Thread.currentThread().getName()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();

        System.out.println("LachCount: " + latch.getCount());
    }
}

public class App {
    public static void main(String[] args) {
        final int NUMBER_OF_REPETITIONS = 10;
        final int NUMBER_OF_THREADS = 3;
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_REPETITIONS);

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        // Starts executing the tasks in parallel
        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            executor.submit(new Processor(latch));
        }

        // awaits until the CountDownLatch is ZERO
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.println("Completed");
    }
}
