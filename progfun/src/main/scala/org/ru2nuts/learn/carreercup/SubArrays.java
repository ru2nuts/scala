package org.ru2nuts.learn.carreercup;

import java.util.Scanner;

/**
 * Created by ru2nuts on 9/19/16.
 */
public class SubArrays {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int r = in.nextInt();
        int c = in.nextInt();

        double volOnRowR = Math.min((0.0 + x) - (r - 1.0) * (r) / 2.0, r);
        double vol = volOnRowR / r; //r = columns count in this row

        System.out.println(vol);
    }

    public static int minSubSizeKOfArrayOfSizeN(int[] birArr, int k) {
        return 0;
    }
}
