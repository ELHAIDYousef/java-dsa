package javaBasics.evenDigits;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        sc.close();

        int counter = 0;
        int original = num;
        while (num != 0) {
            if (num % 2 == 0)
                counter++;
            num /= 10;
        }
        System.out.println("The number of even digits in " + original + " is: " + counter);
    }
}
