package demo;

import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running....");

            // inherits from Object class
            // can cause the thread to be waiting indefinetely without a timeout
            // can be called only inside a syncronize block
            wait();

            // to reach this point
            // another thread with the same intrisic lock must call the method notify
            // so this code would be resumed.
            System.out.println("Resumed.");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return keypressed");
            notify();
            Thread.sleep(5000);
        }
    }
}
