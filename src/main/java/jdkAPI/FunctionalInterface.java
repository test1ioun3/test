package jdkAPI;

import java.util.ArrayList;
import java.util.Comparator;

public class FunctionalInterface {

    public static void main(String[] args) {

        ArrayList<String> testList = new ArrayList<>();
        testList.add("asodnaosnd");
        testList.add("zbui");
        testList.add("y2i312i32i22200");
        testList.sort(new CompareStringByLength());
        System.out.println(testList);
    }
}

class CompareStringByLength implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}