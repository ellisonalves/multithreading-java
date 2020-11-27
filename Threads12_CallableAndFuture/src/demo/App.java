package demo;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {
        Random random = new Random();
        int duration = random.nextInt(4000);

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Starting thread");

            if (duration > 2000) {
                throw new IOException("Sleeping for too long");
            }

            Thread.sleep(duration);

            System.out.println("Finished");

            return duration;
        });

        executorService.shutdown();

        try {
            System.out.println("Duration is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e);
        }
    }
}
