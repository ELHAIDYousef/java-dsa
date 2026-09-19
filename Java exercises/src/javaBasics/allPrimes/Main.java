package javaBasics.allPrimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        for (int j = 2; j < num ; j++) {
            boolean isPrime = j > 1;
            for (int i = 2; i * i <= num && isPrime; i++) {
                if (j % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) System.out.print(j + " - ");

        }
    }
}
