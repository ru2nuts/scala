package org.ru2nuts.learn;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Return all subsets for a set.
 */
public class AllSubsets {

    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e"}));
        HashSet<HashSet<String>> allSubsets = getAllSubsets(set);
        assert allSubsets.size() == Math.pow(2, set.size());

        for (HashSet<String> subset : allSubsets) {
            for (String st : subset) {
                System.out.print(st);
            }
            System.out.println();
        }
        //return 0;
    }

    private static HashSet<HashSet<String>> getAllSubsets(HashSet<String> set) {
        int size = set.size();
        if (size == 0 || size == 1) {
            HashSet<HashSet<String>> res = new HashSet<HashSet<String>>();
            res.add(set);
            return res;
        }
        HashSet<HashSet<String>> res = new HashSet<HashSet<String>>();
        for (String st : set) {
            HashSet<String> subset = (HashSet<String>) set.clone();
            subset.remove(st);
            res.add(subset);
            res.addAll(getAllSubsets(subset));
        }
        return res;
    }
}
