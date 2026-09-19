package javaBasics.palindromeNumber;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt(),
                  original = num ,
                  reversed = 0;

        sc.close();

        while (num != 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }

        System.out.println( original +  (reversed == original ? " is" : " is not") + " a palindrome.");
    }
}
