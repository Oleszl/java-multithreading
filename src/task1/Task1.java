package task1;

import java.util.Optional;

public class Task1 extends Thread {
    private final int number;

    private final Result result;

    public Task1(int number, Result result) {
        Optional.ofNullable(result).orElseThrow(() -> new IllegalArgumentException("Result can't be null!"));
        this.result = result;
        this.number = number;
    }

    @Override
    public void run() {
        calculation(number);
    }

    public synchronized void calculation(int number) {
        for (int i = 0; i <= number; i++) {
            if (i % 2 == 0)
                result.addValue(i);
        }
    }
}
