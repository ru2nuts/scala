package org.ru2nuts.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Return all subsets for a set.
 */
public class AllSubsets {

    public static void main(String[] args) {
        List<String> set = new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e"}));
        List<List<String>> allSubsets = getSubSets(set, 0);
        assert allSubsets.size() == Math.pow(2, set.size());

        for (List<String> subset : allSubsets) {
            for (String st : subset) {
                System.out.print(st);
            }
            System.out.println("--");
        }
    }

    private static <T> List<List<T>> getSubSets(List<T> set, int index) {
        // base case - return a list with an empty set
        if (index == set.size()) {
            List<T> emptySet = new ArrayList<T>();
            List<List<T>> baseList = new ArrayList<List<T>>();
            baseList.add(emptySet);
            return baseList;
        }

        List<List<T>> res = new ArrayList<List<T>>();

        // recurse with incremented index, will return all subsets without the current element
        List<List<T>> subsets = getSubSets(set, index + 1);

        // add subsets without the current element to the result
        res.addAll(subsets);

        T currentSubset = set.get(index);
        for (List<T> subset : subsets) {
            List<T> additionalSubset = new ArrayList<T>();
            additionalSubset.addAll(subset);
            additionalSubset.add(currentSubset);
            // add subsets WITH the current element to the results
            res.add(additionalSubset);
        }
        return res;
    }
}
