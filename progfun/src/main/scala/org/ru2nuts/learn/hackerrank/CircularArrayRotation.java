package org.ru2nuts.learn.hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * https://www.hackerrank.com/challenges/circular-array-rotation
 */
public class CircularArrayRotation {
    public static void main(String[] args) {
        Scanner sc = new java.util.Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt() % n;
        int q = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] m = new int[q];
        for (int i = 0; i < q; i++) {
            m[i] = sc.nextInt();
        }

        for (int i = 0; i < q; i++) {
            System.out.println(a[getNewIndex(n, k, m[i])]);
        }
    }

    static int getNewIndex(int n, int k, int i) {
        return (i - k + n) % n;
    }

}
