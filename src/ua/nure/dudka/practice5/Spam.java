package ua.nure.dudka.practice5;

import java.util.Scanner;

public class Spam {
    private Thread[] threads;
    private static final String[] MESSAGES = {"qwe", "asdasd", "zxczxczxc"};
    private static final int[] TIMES = {333, 1500, 555};

    Spam(String[] messages, int[] times) {
        threads = new Thread[messages.length];

        for (int i = 0; i < messages.length; i++) {
            threads[i] = new Worker(messages[i], times[i]);
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    static class Worker extends Thread {
        private String message;
        private int time;

        Worker(String message, int time) {
            this.message = message;
            this.time = time;
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                System.out.println(message);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Spam spam = new Spam(MESSAGES, TIMES);

        spam.start();

        Scanner scanner = new Scanner(System.in, "Cp1251");
        while (true) {
            if (scanner.nextLine().isEmpty()) {
                spam.stop();
                break;
            }
        }
    }
}
