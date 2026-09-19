package javaBasics.isPrime;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        sc.close();

        boolean isPrime = num > 1;

        for (int i = 2; i * i <= num && isPrime; i++) {
            if (num % i == 0) {
                isPrime = false;
            }
        }

        System.out.println(
                num + (isPrime ? " is prime." : " is not prime.")
        );
    }
}
