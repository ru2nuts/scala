package org.ru2nuts.learn;

import java.util.Scanner;

public class FactorialZeros {

    public static void main(String[] args) {
        while (true) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int tens = 0;
            int fives = 0;
            int evenButNotTens = 0;
            for (int i = 1; i <= n; i++) {
                int lastDigit = i % 10;
                if (lastDigit == 0)
                    tens++;
                else if (lastDigit % 2 == 0)
                    evenButNotTens++;
                else if (lastDigit == 5)
                    fives++;
            }

            int zc = tens + Math.min(fives, evenButNotTens);
            System.out.println(zc);
        }
    }
}
