package javaBasics.overloadedMax;

public class Main {
    public static void main(String[] args) {

        System.out.println(max(10, 20));
        System.out.println(max(100L, 50L));
        System.out.println(max(10.5, 20.5));
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static long max(long a, long b) {
        return (a > b) ? a : b;
    }

    static double max(double a, double b) {
        return (a > b) ? a : b;
    }

}