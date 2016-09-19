package org.ru2nuts.learn.carreercup;

import java.util.*;

/**
 * Created by ru2nuts on 9/18/16.
 */
public class AbcStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        ArrayList<String> chars = new ArrayList<String>(Arrays.asList(new String[]{"a", "b", "c"}));

        HashSet<String> allCombinations = recur(chars, 3);
        for (String c : allCombinations) {
            System.out.println(c);
        }
        System.out.println("Size:" + allCombinations.size());

        int countValid = 0;
        for (String c : allCombinations) {
            if (!c.contains("ccc") && (c.indexOf('b') == -1 || c.indexOf('b') == c.lastIndexOf('b'))) {
                System.out.println(c);
                countValid++;
            }
        }
        System.out.println("Valid:" + countValid);

    }

    public static HashSet<String> recur(List<String> availableChars, int len) {
        if (len == 1) {
            HashSet<String> res = new HashSet<>();
            for (String ch : availableChars) {
                res.add(ch);
            }
            return res;
        }
        HashSet<String> results = new HashSet<>();

        for (int pos = 0; pos < len; pos++) {
            for (String ch : availableChars) {
                HashSet<String> intermediateResults = recur(availableChars, len - 1);

                for (String intermediateResult : intermediateResults) {
                    results.add(insertCharAt(intermediateResult, ch, pos));
                }
            }
        }
        return results;
    }

    public static String insertCharAt(String str, String ch, int pos) {
        return str.substring(0, pos) + ch + str.substring(pos);
    }
}
