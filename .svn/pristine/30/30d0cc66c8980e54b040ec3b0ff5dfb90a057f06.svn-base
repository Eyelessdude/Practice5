package ua.nure.dudka.practice5;

public class Part3 {
    private static final int THREADS_NUMBER = 3;
    private static final int THREAD_DELAY = 10;
    private static final int THREAD_SLEEP = 1000;
    private int firstCounter;
    private int secondCounter;

    public void thread() {
        firstCounter = 0;
        secondCounter = 0;
        callThread();
    }

    private void callThread() {
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    super.run();
                    System.out.println(firstCounter - secondCounter);
                    firstCounter++;
                    try {
                        Thread.sleep(THREAD_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    secondCounter++;

                }
            };
            t.start();

        }
    }

    public void synchroThread() {
        firstCounter = 0;
        secondCounter = 0;
        Part3 p = new Part3();
        synchronized (p) {
            p.callThread();
        }

    }

    public static void main(String[] args)  {
        Part3 part3 = new Part3();
        part3.synchroThread();
        System.out.println("------");
        try {
            Thread.sleep(THREAD_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        part3.thread();
    }
}
