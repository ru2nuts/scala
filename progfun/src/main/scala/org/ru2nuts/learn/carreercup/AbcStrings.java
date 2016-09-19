package org.ru2nuts.learn.carreercup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ru2nuts on 9/18/16.
 */
public class AbcStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        ArrayList<String> chars = new ArrayList<String>(Arrays.asList(new String[]{"a", "b", "c"}));

        ArrayList<String> allCombinations = recur(chars, n);
        for (String c : allCombinations) {
            System.out.println(c);
        }
    }

    public static ArrayList<String> recur(ArrayList<String> availableChars, int len) {
        if (len == 1) {
            ArrayList<String> res = new ArrayList<>();
            for (String ch : availableChars) {
                res.add(ch);
            }
            return res;
        }
        ArrayList<String> results = new ArrayList<>();
        ArrayList<String> intermediateResults = recur(availableChars, len - 1);
        for (String intermediateResult : intermediateResults) {
            for (String ch : availableChars) {
                for (int i = 0; i < len; i++) {
                    results.add(insertChar(intermediateResult, ch, i));
                }
            }
        }
        return results;
    }

    public static String insertChar(String str, String ch, int pos) {
        if (pos > str.length() || ch.length() != 1) {
            throw new RuntimeException();
            //return str;
        }
        return str.substring(0, pos) + ch + str.substring(pos);
    }
}
