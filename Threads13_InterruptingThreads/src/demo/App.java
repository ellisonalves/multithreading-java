package demo;

import java.util.Random;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");
        Thread t1 = new Thread(() -> {
            Random random = new Random();

            for (int i = 0; i < 1E8; i++) {
                // One way of checking if the thread was interrupted is reading its state using Thread class
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted!");
                    break;
                }

                // Another way is, if you have a Thread.sleep
                // if the thread that was put to sleep is interrupted
                // Thread.sleep will throw an exception and it can be handled inside of the catch clause.
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                    break;
                }

                Math.sin(random.nextDouble());
            }
        });
        t1.start();

        Thread.sleep(500);

        // doesn't really interrupts the thread
        // it flags the thread so it can be interrupted
        t1.interrupt();

        t1.join();

        System.out.println("Finished");
    }
}
