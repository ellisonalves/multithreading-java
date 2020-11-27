package synchronization;

import java.util.LinkedList;
import java.util.Random;

public class ProcessorQueue {

    private static final int THREAD_RANDOM_SLEEPING_TIME = 500;
    private final LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;

    // It is necessary to call wait and notify on the locked object
    private Object lock = new Object();

    private Random random = new Random();

    public void produce() throws InterruptedException {

        int value = 0;
        while (true) {
            synchronized (lock) {
                // keeps the current thread waiting until the release condition is not satisfied by
                // locking the intrinsic lock so the current thread must have to wait until it is released.
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);

                // notifies the threads mechanism that some thread can be awakened
                lock.notify();
            }
            Thread.sleep(random.nextInt(THREAD_RANDOM_SLEEPING_TIME));
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }

                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);

                // notifies the threads mechanism that some thread can be awakened
                lock.notify();
            }
            Thread.sleep(random.nextInt(THREAD_RANDOM_SLEEPING_TIME));
        }
    }
}
