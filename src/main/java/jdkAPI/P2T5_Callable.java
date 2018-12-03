package jdkAPI;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class P2T5_Callable {

    public static void main(String[] args) throws Exception {
        long startTime, endTime;
        Random random = new Random();
        int[]  data   = new int[99999];
        for (int i=0; i<data.length; i++) {
            data[i] = random.nextInt();
        }
        FindMax task1 = new FindMax(data, 0, data.length/2);
        FindMax task2 = new FindMax(data, data.length/2, data.length);
        ExecutorService service = Executors.newFixedThreadPool(2);

        startTime = System.currentTimeMillis();
        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);
        endTime = System.currentTimeMillis();
        int maxValue = Math.max(future1.get(), future2.get());
        long timeUsed = endTime - startTime;

        System.out.println("max Value is " + maxValue + "  time used is " + timeUsed);
    }
}

class FindMax implements Callable<Integer> {
    private int[] data;
    private int start;
    private int end;

    FindMax(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int max = Integer.MIN_VALUE;
        for (int i=start; i<end; i++) {
            if (data[i] > max)
                max = data[i];
        }
        return max;
    }
}
