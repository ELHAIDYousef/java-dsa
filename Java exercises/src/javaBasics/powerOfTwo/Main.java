package javaBasics.powerOfTwo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        sc.close();

        System.out.println(n + (isPowerOfTwo(n)
                ? " is a power of two."
                : " is not a power of two."));
    }

    static boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }

        return n == 1;
    }
}