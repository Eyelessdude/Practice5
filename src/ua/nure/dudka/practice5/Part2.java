package ua.nure.dudka.practice5;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Part2 {
    private static final InputStream CACHED_INPUT_STREAM = System.in;
    private static final int THREAD_TO_SLEEP = 2000;
    private static final int WINDOWS_CALLS = 2;
    private static byte[] lineSeparator;
    private static int callsCount;

    public static void main(String[] args) {
        lineSeparator = System.lineSeparator().getBytes(StandardCharsets.ISO_8859_1);

        InputStream stream = new InputStream() {
            @Override
            public int read() {
                callsCount++;
                if (callsCount == 1) {
                    try {
                        Thread.sleep(THREAD_TO_SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    return lineSeparator[0];
                } else if (lineSeparator.length == WINDOWS_CALLS && callsCount == WINDOWS_CALLS) {
                    return lineSeparator[1];
                } else {
                    return -1;
                }
            }
        };

        System.setIn(stream);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                Spam.main(null);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        System.setIn(CACHED_INPUT_STREAM);
    }
}
