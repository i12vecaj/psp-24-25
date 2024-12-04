class Counter {
    public int count = 0;

    public synchronized void increment() {
        count += 1000;
    }
}

class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
    }
}

class CounterRunnable implements Runnable {
    private Counter counter;

    public CounterRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
    }
}

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread[] threads = new Thread[5];

        // Using Thread class
        for (int i = 0; i < 5; i++) {
            threads[i] = new CounterThread(counter);
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final count using Thread: " + counter.count);

        // Reset counter
        counter.count = 0;

        // Using Runnable interface
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new CounterRunnable(counter));
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final count using Runnable: " + counter.count);
    }
}
