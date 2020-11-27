package demo;

class Runner implements Runnable {
    private int count;

    private synchronized void increment() {
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            increment();
        }
    }

    public int getResult() {
        return count;
    }
}

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    public void doWork() {
        Runner theRunner = new Runner();
        Thread t1 = new Thread(theRunner);
        Thread t2 = new Thread(theRunner);

        t1.start();
        t2.start();

        try {
            // waits the thread t1 to finish
            t1.join();
            // waits the thread t2 to finish
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + theRunner.getResult());
    }
}
