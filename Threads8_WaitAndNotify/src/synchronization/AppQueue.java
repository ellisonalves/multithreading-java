package synchronization;

public class AppQueue {

    public static void main(String[] args) throws InterruptedException {
        ProcessorQueue processor = new ProcessorQueue();

        // running producer in one thread
        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // running producer in other thread
        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // start threads
        t1.start();
        t2.start();

        // waits for threads to finish before terminate the program
        t1.join();
        t2.join();
    }

}
