package org.ru2nuts.learn;

/**
 * Created by ru2nuts on 9/21/16.
 */
public class CircularArrayCircle {
    public static void main(String[] args) {

        int[] a = {2, 0, 5, 0, 0, 9, 0, 0, 0, 15, 0, 0, 0, 0, 1, 0};

        //2->5->9->15->0
        //TODO: implement with fast and slow pointers

        int i = 0;
        int distance = 0;
        int steps = 0;

        while (steps <= a.length) {

            //if i - valid
            if (i >= 0 && i < a.length) {
                int nextI = a[i];
                if (nextI > i + 1) {
                    distance += nextI - i;
                } else if (nextI < i + 1) {
                    System.out.println("Done, i: " + i);
                    System.out.println("nextI: " + nextI);
                    System.out.println("distancez: " + distance);
                    return;
                } else {
                    distance++;
                }
                if (distance > a.length && nextI >= 0 && nextI < a.length) {
                    System.out.println("Done, i: " + i);
                    System.out.println("nextI: " + nextI);
                    System.out.println("distance: " + distance);
                    return;
                }
                i = nextI;
            } else {
                System.out.println("invalid pointer, i: " + i);
                return;
            }
            steps++;
        }
    }
}
