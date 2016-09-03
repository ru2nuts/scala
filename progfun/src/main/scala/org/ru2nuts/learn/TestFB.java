package org.ru2nuts.learn;

import java.util.ArrayList;

/**
 * Created by ru2nuts on 9/1/16.
 */
public class TestFB {

    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.

        System.out.println((new TestFB()).sqRt(4));
        System.out.println((new TestFB()).sqRt(16));
        System.out.println((new TestFB()).sqRt(25));
        System.out.println((new TestFB()).sqRt(1000));
        System.out.println((new TestFB()).sqRt(9));
        System.out.println((new TestFB()).sqRt(0));
        System.out.println((new TestFB()).sqRt(10));

        String[] dict = {"a", "the", "main", "theory", "apple", "design"};
        System.out.println((new TestFB()).insertSpaces("themainappleatheoryofthedesigns", dict));

    }


    int sqRt(int a) {
        double b = 1;

        while (true) {
            double x = b * b;

            if (isClose(x - a))
                return (int) Math.floor(b);
            else {
                b = (b + a / b) / 2;
            }
        }
    }

    boolean isClose(double x) {
        return (Math.abs(x) < 0.01);
    }


    String insertSpaces(String s, String[] dict) {
        StringBuffer res = new StringBuffer();
        String currentWord = "";
        String restOfInput = s;
        while (!restOfInput.isEmpty()) {
            String fl = restOfInput.substring(0, 1);
            for (int i = 0; i < dict.length; i++) {
                String nextWord = dict[i];
                if (nextWord.startsWith(fl)
                        && restOfInput.startsWith(nextWord)
                        && currentWord.length() < nextWord.length()) {
                    currentWord = nextWord;
                }
            }
            if (res.length() > 0)
                res.append(' ');
            if (currentWord.isEmpty()) {
                res.append('(').append(fl).append(')').toString();
                restOfInput = restOfInput.substring(1);
            } else {
                res.append(currentWord);
                restOfInput = restOfInput.substring(currentWord.length());
                currentWord = "";
            }
        }
        return res.toString();
    }


}