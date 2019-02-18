package ua.nure.dudka.practice5;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;


public class Part4 {
    private static final String FILE_NAME = "part4.txt";
    private static int result;

    static synchronized void setMax(int max) {
        if (max > result) {
            result = max;
        }
    }

    static int getStringMaxNumber(String input) {
        int max = 0;
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

            if (nextInt > max) {
                max = nextInt;
            }
        }

        scanner.close();
        return max;
    }

    public static void main(String[] args) {
        int maxInCycle = 0;
        long startTime = 0;
        long endTime;

        List<FindMaxNumber> runnables = new ArrayList<>();

        try (FileInputStream fileReader = new FileInputStream(FILE_NAME);
             InputStreamReader inputStreamReader = new InputStreamReader(fileReader, "Cp1251");
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                startTime = System.currentTimeMillis();
                FindMaxNumber runnable = new FindMaxNumber(line);
                runnables.add(runnable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < runnables.size(); i++) {
            runnables.get(i).thread.start();
        }

        for (FindMaxNumber runnable : runnables) {
            try {
                runnable.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println(Part4.result);
        System.out.println(endTime - startTime);

        try (FileInputStream fileReader = new FileInputStream(FILE_NAME);
             InputStreamReader inputStreamReader = new InputStreamReader(fileReader, "Cp1251");
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            startTime = System.currentTimeMillis();
            String line;
            while ((line = reader.readLine()) != null) {
                int tempMax = getStringMaxNumber(line);
                if (tempMax > maxInCycle) {
                    maxInCycle = tempMax;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        endTime = System.currentTimeMillis();
        System.out.println(maxInCycle);
        System.out.println(endTime - startTime);
    }
}
