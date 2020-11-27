package demo;

import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection instance = new Connection();

    // creates a semaphore with the number of concurrently executing threads permitted
    private Semaphore sem = new Semaphore(5);

    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            sem.release();
        }
    }

    private void doConnect() {
        synchronized (this) {
            connections++;
            System.out.println("Current connections; " + connections);
        }

        try {
            // Allowing a con
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
    }

}
