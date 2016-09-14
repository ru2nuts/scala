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
        assert allSubsets.size() == Math.pow(2, set.size()) - 1;

        for (HashSet<String> subset : allSubsets) {
            for (String st : subset) {
                System.out.print(st);
            }
            System.out.println("--");
        }
    }

    private static <T> HashSet<HashSet<T>> getAllSubsets(HashSet<T> set) {
        int size = set.size();
        if (size == 0) {
            HashSet<HashSet<T>> res = new HashSet<HashSet<T>>();
            res.add(set);
            return res;
        }
        HashSet<HashSet<T>> res = new HashSet<HashSet<T>>();
        for (T st : set) {
            HashSet<T> subset = (HashSet<T>) set.clone();
            subset.remove(st);
            res.add(subset);
            res.addAll(getAllSubsets(subset));
        }
        return res;
    }
}
