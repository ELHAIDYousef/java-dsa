package javaBasics.digitsSum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        sc.close();

        int sum = 0;
        int original = num;
        while( num!=0 ){
            sum += num % 10;
            num /= 10;
        }
        System.out.printf("The sum of the digits of %d is: %d", original, sum);
    }
}
