package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
        // usingSemaphore();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executor.submit(() -> {
                Connection.getInstance().connect();
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    private static void usingSemaphore() throws InterruptedException {
        // It is used to control access to some resource given a number of permits
        Semaphore sem = new Semaphore(1);

        // acquires a permit from semaphore
        // reducing the number of available permits by one
        sem.acquire();

        // releases a permit from semaphore
        // increasing the number of available permits by one
        sem.release();

        System.out.println("Available permits: " + sem.availablePermits());
    }
}
