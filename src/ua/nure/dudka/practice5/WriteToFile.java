package ua.nure.dudka.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;

public class WriteToFile extends Thread {
    private AtomicInteger rowIndex = new AtomicInteger();
    private final RandomAccessFile randomAccessFile;
    private static final int ROW_LENGTH = 20;
    Thread thread;


    WriteToFile(RandomAccessFile randomAccessFile, int rowIndex) {
        this.rowIndex.set(rowIndex);
        this.randomAccessFile = randomAccessFile;
        this.thread = new Thread();
    }

    @Override
    public void run() {
        int startIndex = (ROW_LENGTH + System.lineSeparator().length()) * rowIndex.get();
        int endIndex = startIndex + ROW_LENGTH;

        try {
            for (int i = startIndex; i < endIndex; i++) {
                Thread.sleep(1);
                writeWordIndex(i);
            }
            writeLineSeparator(endIndex);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void writeLineSeparator(int index) throws IOException {
        synchronized (randomAccessFile) {
            randomAccessFile.seek(index);
            randomAccessFile.writeBytes(System.lineSeparator());
        }
    }

    private void writeWordIndex(int index) throws IOException {
        synchronized (randomAccessFile) {
            randomAccessFile.seek(index);
            randomAccessFile.writeByte('0' + rowIndex.get());
        }
    }
}
