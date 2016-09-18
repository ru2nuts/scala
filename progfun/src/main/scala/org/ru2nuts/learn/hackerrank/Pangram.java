package org.ru2nuts.learn.hackerrank;

import java.util.Scanner;

/**
 * Created by ru2nuts on 9/17/16.
 */
public class Pangram {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new java.util.Scanner(System.in);
        String s = sc.nextLine().toLowerCase();

        boolean isPangram = true;

        for (char c = 'a'; c <= 'z'; c++) {
            if (s.indexOf(c) == -1) {
                isPangram = false;
            }
        }

        if (!isPangram) System.out.print("not ");
        System.out.println("pangram");
    }
}

