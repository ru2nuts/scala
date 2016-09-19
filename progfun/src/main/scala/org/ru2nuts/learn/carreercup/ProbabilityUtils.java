package org.ru2nuts.learn.carreercup;

import java.util.Scanner;

/**
 * Created by ru2nuts on 9/18/16.
 */
public class ProbabilityUtils {

    public double nChooseKF(int n, int k) {
        return factorialI(n) / (factorialI(k) * factorialI(n - k));
    }

    public double nChooseKI(int n, int k) {
        double res = 1.0;
        for (int i = 1; i <= k; i++) {
            res *= (1.0 + n - i) / (0.0 + i);
        }
        return res;
    }

    public double factorialI(int n) {
        double res = 1.0;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public double factorialR(int n) {
        if (n == 1)
            return 1;
        return n * factorialR(n - 1);
    }

    public double catalanF(int n) {
        double nf = factorialI(n);
        return factorialI(2 * n) / (Math.pow(nf, 2) * (n + 1));
    }

    public double catalanI(int n) {
        double res = 1.0;
        for (int i = 2; i <= n; i++) {
            res *= (0.0 + n + i) / (0.0 + i);
        }
        return res;
    }
}
