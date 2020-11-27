package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
    private final int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(String.format("Thread :%s STARTED Processor Id: %s", Thread.currentThread().getName(), id));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Thread :%s COMPLETED Processor Id: %s", Thread.currentThread().getName(), id));
    }
}

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executor.submit(new Processor(i));
        }

        executor.shutdown();
        System.out.println("All tasks submitted");

        try {
            // Throws TimeoutException if the process is not finished in the specified time.
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed");
    }
}
