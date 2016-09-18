package org.ru2nuts.learn.hackerrank;

import java.util.Scanner;

/**
 * Created by ru2nuts on 9/17/16.
 */
public class BeautifulString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();

        int replCount = 0;
        boolean foundBefore = false;

        for (int i = 0; i < s.length(); ) {
            boolean startsWith = s.substring(i).startsWith("010");

            if (startsWith) {
                if (!foundBefore) {
                    foundBefore = true;
                    replCount++;
                } else if (foundBefore) { // skip every other one of overlapping
                    foundBefore = false;
                }
                i += "010".length() - 1; // next - check starting from the last '0' in '010' to see overlap
            } else {
                foundBefore = false;
                i++;
            }
        }
        System.out.println(replCount);
    }
}

