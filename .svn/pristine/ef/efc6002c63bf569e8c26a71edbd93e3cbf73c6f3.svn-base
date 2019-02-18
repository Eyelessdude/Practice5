package ua.nure.dudka.practice5;

public class Part1 {
    private static final int MAX_THREADS_NUMBER = 4;
    private static final int THREAD_SLEEP = 500;

    private static void print() {
        for (int i = 0; i < MAX_THREADS_NUMBER; i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(THREAD_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args)  {
        Thread thread;

        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                print();
            }
        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        thread = new Thread(Part1::print);

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
