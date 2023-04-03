import task1.Result;
import task1.Task1;
import task2.Prime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> primeNumbers = new ArrayList<>();

        int value1 = generateNaturalNumber(50);
        int value2 = generateNaturalNumber(10);

        Result result = new Result();
        Task1 t1 = new Task1(value1, result);
        Task1 t2 = new Task1(value2, result);

        t1.start();
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("First number: %d, second number: %d, \nResult: %d\n", value1, value2, result.getValue());

        System.out.println("Please, provide the primes range:");
        System.out.print("FROM:");
        int fromInt = scanner.nextInt();
        System.out.print("TO:");
        int toInt = scanner.nextInt();

        for (int i = fromInt; i <= toInt; i++) {
            Prime customThread = new Prime();
            customThread.setNumber(i);
            customThread.setList(primeNumbers);
            customThread.start();
            synchronized (customThread) {
                try {
                    customThread.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.printf("Prime range is from [%d] to [%d]\n", fromInt, toInt);
        System.out.println("The number of prime numbers in this range is: " + primeNumbers.size());
        System.out.print("List of Prime numbers: " + primeNumbers);
    }

    private static int generateNaturalNumber(int bound) {
        Random random = new Random();
        int number = random.nextInt(bound);
        if (!isNatural(number)) {
            throw new RuntimeException("Generated number is not Natural");
        }
        return number;
    }

    private static boolean isNatural(int number) {
        return number > 0;
    }
}
