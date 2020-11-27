package demo;

import java.util.Scanner;

class Processor extends Thread {

    // prevents the thread from caching the variables' value and never updating it
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {

    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press RETURN to stop");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();
    }
}
