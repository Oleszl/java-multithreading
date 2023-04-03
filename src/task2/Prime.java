package task2;

import java.util.ArrayList;
import java.util.List;

public class Prime extends Thread{
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Integer> list = new ArrayList<>();

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        primeNumbers(number, list);
    }

    public synchronized void primeNumbers(int number, List<Integer> primes){
        boolean flag = true;
        for (int j = 2; j <= number / 2; j++) {
            if (number % j == 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            primes.add(number);
        }
        notify();
    }
}
