package jdkAPI;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class P2T3 {

    public static void main(String[] args) throws Exception{

        byte[] input = new byte[10];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 10 characters, including blanks:  ");
        String line = sc.nextLine();
        sc.close();
        InputStream in = new ByteArrayInputStream(line.getBytes());
        BufferedInputStream bis = new BufferedInputStream(in, 2);
        System.out.println(bis.read());
        System.out.println("byte numbers:  " + in.read(input,0,10));

        for (int i=0; i<input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
