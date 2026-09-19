package javaBasics.binaryToDecimal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a binary number: ");
        String binary = sc.nextLine();

        sc.close();

        int result = 0;

        for (int i = 0; i < binary.length(); i++) {
            int digit = binary.charAt(i) - '0';
            result = result * 2 + digit;
        }

        System.out.println("The decimal result is: " + result);
    }
}