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
            String currentChar = str.substring(0, 1);
            ArrayList<String> subResults = recur(str.substring(1));
            for (String subResult : subResults) {
                //insert the current char into each position in the sub-results
                for (int pos = 0; pos <= subResult.length(); pos++) {
                    res.add(subResult.substring(0, pos) + currentChar + subResult.substring(pos));
                }
            }
        }
        return res;
    }
}