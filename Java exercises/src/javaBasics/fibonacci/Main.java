package javaBasics.fibonacci;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        sc.close();

        int result;

        if (num == 0) {
            result = 0;
        } else if (num == 1) {
            result = 1;
        } else {
            int previous = 0;
            int current = 1;

            for (int i = 2; i <= num; i++) {
                int next = previous + current;
                previous = current;
                current = next;
            }

            result = current;
        }

        System.out.printf("The Fibonacci of %d is: %d%n", num, result);
    }
}
