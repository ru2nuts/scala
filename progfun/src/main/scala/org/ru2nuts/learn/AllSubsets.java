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

        // recurse with incremented index, will return all subsetsWithoutCurrentElement without the current element
        List<List<T>> subsetsWithoutCurrentElement = getSubSets(set, index + 1);

        // add subsetsWithoutCurrentElement without the current element to the result
        res.addAll(subsetsWithoutCurrentElement);

        T currentElement = set.get(index);
        for (List<T> subset : subsetsWithoutCurrentElement) {
            List<T> subsetsWithCurrentElement = new ArrayList<T>();
            subsetsWithCurrentElement.addAll(subset);
            subsetsWithCurrentElement.add(currentElement);
            // add subsetsWithoutCurrentElement WITH the current element to the results
            res.add(subsetsWithCurrentElement);
        }
        return res;
    }
}
