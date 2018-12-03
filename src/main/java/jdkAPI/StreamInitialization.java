package jdkAPI;

import java.util.ArrayList;
import java.util.Random;

public class StreamInitialization {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        ArrayList<Integer> listInteger = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();
        secondList.stream().count();
        Random random = new Random();

        int testLoopTime = 30;
        double avgTimeUsed = 0;
        for (int k=0; k<testLoopTime; k++) {
//            ArrayList<Integer> listInteger = new ArrayList<>();
            listInteger.clear();
            for (int i=0; i<9999999; i++) {
                listInteger.add(random.nextInt());
            }
            startTime = System.currentTimeMillis();
            listInteger.stream().max(Integer::compareTo);
            endTime = System.currentTimeMillis();
            long timeUsed = endTime-startTime;
            System.out.println("stream " + (k+1) + " time(s) used:  " + timeUsed + " ms.");
            avgTimeUsed += timeUsed;
        }
        avgTimeUsed /= testLoopTime;
        System.out.println("average Time used is " + avgTimeUsed + " ms.");
    }
}
