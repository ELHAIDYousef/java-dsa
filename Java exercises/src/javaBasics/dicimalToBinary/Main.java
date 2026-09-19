package javaBasics.dicimalToBinary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        sc.close();

        StringBuilder binary = new StringBuilder();

        while (num != 0) {
            binary.append(num % 2);
            num /= 2;
        }

        binary.reverse();

        System.out.println("The binary result is: " + binary);
    }
}
