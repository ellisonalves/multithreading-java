package demo;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;

    // Equivalent to synchronize block
    // Allow us to lock/unlock a block of code to be accessed by just one thread at time.
    private Lock lock = new ReentrantLock();

    // Equivalent to wait/notify
    // Allow us to awake a thread that is waiting to acquire this lock
    private Condition condition = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("firstThread is Waiting....");
        // make the thread wait and unlock
        condition.await();

        System.out.println("firstThread is Woken up");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("secondThread is started");
        lock.lock();

        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");

        // awakes one waiting thread only
        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() throws InterruptedException {
        System.out.println("Count is: " + count);
    }
}
