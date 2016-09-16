package org.ru2nuts.learn;

public class NewtonBabylonianSquareRoot {

    public static void main(String[] args) {
        NewtonBabylonianSquareRoot inst = new NewtonBabylonianSquareRoot();
        System.out.println(inst.sqRtInt(16));
        System.out.println(inst.sqRtInt(100));
        System.out.println(inst.sqRtInt(1000000));
        System.out.println(inst.sqRtInt(1000000000));
        System.out.println(inst.sqRtInt(2));
        System.out.println(inst.sqRtInt(4));
        System.out.println(inst.sqRtInt(64));
        System.out.println(inst.sqRtInt(25));
        System.out.println(inst.sqRtInt(49));
        System.out.println(inst.sqRtInt(9));
        System.out.println(inst.sqRtInt(81));
    }

    double sqRt(int target) {
        double guess = 1;
        while (true) {
            double guessSquared = guess * guess;

            if (isClose(guessSquared - target))
                return guess;
            else
                guess = (guess + target / guess) / 2;
        }
    }

    int sqRtInt(int a) {
        return (int) Math.floor(sqRt(a));
    }

    boolean isClose(double x) {
        return (Math.abs(x) < 0.01);
    }
}
