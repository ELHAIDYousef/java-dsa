package javaBasics.sumToN;

import java.util.Scanner;

public class Main {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number: ");
        int num = sc.nextInt(), sum = 0;

        sc.close();

//        Mathematic formula solution
//        long sum = (long) num * (num + 1) / 2;

        for (int i = 1; i <= num; sum += i++) ;

        System.out.println("The sum to " + num + " is: " + sum);

    }
}
