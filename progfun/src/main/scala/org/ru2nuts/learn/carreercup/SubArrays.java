package org.ru2nuts.learn.carreercup;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by ru2nuts on 9/19/16.
 */
public class SubArrays {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int[] mins = minSubSizeKOfArrayOfSizeN(arr, k);

        for (int i = 0; i < n - k + 1; i++) {
            System.out.println(mins[i]);
        }

        mins = minSlideArray(arr, k);
        for (int i = 0; i < n - k + 1; i++) {
            System.out.println(mins[i]);
        }
    }

    public static int[] minSubSizeKOfArrayOfSizeN(int[] bigArr, int k) {

        int n = bigArr.length;
        int subArraysCount = n - k + 1;
        int[] mins = new int[subArraysCount];
        for (int subArrayPosition = 0; subArrayPosition < subArraysCount; subArrayPosition++) {
            int[] subArray = Arrays.copyOfRange(bigArr, subArrayPosition, subArrayPosition + k);

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < subArray.length; i++) {
                min = Math.min(min, subArray[i]);
            }
            mins[subArrayPosition] = min;
        }
        return mins;
    }

    public static int[] minSlideArray(int[] ar, int k) {

        int n = ar.length;
        int[] res = new int[n - k + 1];

        Deque<Integer> d = new ArrayDeque<Integer>(k);

        // read first k elements of input array,
        // push the index of the first one to the back of deque,
        // then -
        //     if any subsequent elements are smaller,
        //       replace the value in the back with the index of the smaller element
        //     else
        //       simply add the index to the back
        for (int i = 0; i < k; i++) {
            while ((!d.isEmpty() && ar[i] <= ar[d.getLast()]))
                d.pollLast();

            d.addLast(i);
        }
        for (int i = k; i < ar.length; i++) {
            res[i - k] = ar[d.getFirst()];
            //Removing front element out of windows
            while ((!d.isEmpty() && d.getFirst() <= (i - k)))
                d.pollFirst();

            while ((!d.isEmpty() && ar[i] <= ar[d.getLast()]))
                d.pollLast();

            d.addLast(i);
        }
        res[n - k] = ar[d.getFirst()];
        return res;
    }

}
