package javaBasics.factorialTrailingZeros;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        sc.close();

        System.out.println("Trailing zeros in " + n + "! : " + trailingZeros(n));
    }

    static int trailingZeros(int n) {
        int factorial = 1;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }

        int counter = 0;

        while (factorial % 10 == 0) {
            factorial /= 10;
            counter++;
        }

        return counter;
    }
}