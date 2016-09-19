package org.ru2nuts.learn.carreercup;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ru2nuts on 9/18/16.
 */
public class StrPermutations {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        ArrayList<String> res = recur(s);
        for (String resS : res) {
            System.out.println(resS);
        }
        System.out.println(res.size());
    }

    public static ArrayList<String> recur(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str.length() == 1) {
            res.add(str);
        } else {
            // remove and keep first char
            String currentChar = str.substring(0, 1);
            // call recursivelly for remainder of the string
            ArrayList<String> subResults = recur(str.substring(1));
            // for each result - insert the kept character into each possible position - 0..len
            // note that position 'len' is AFTER the last character of the sub-result
            for (String subResult : subResults) {
                //insert the current char into each position in the sub-results
                for (int pos = 0; pos <= subResult.length(); pos++) {
                    res.add(insertCharAt(subResult, currentChar, pos));
                }
            }
        }
        return res;
    }

    /**
     * Insert the character at given position, incrementing string length. No characters are replaced.
     *
     * @param str input string
     * @param ch  inserted character
     * @param pos position of insert
     * @return string with character inserted at given position
     */
    public static String insertCharAt(String str, String ch, int pos) {
        return str.substring(0, pos) + ch + str.substring(pos);
    }
}