package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class P3T4_hashCode {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        P3T4_hashCode.class.getResourceAsStream("/welcomeInfo.txt")
                )
        );
        reader.lines().forEach(System.out::println);
        reader.close();
    }

}
