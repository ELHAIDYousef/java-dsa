package javaBasics.digitsCount;

import java.util.Scanner;

public class Main {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num = sc.nextInt();
        sc.close();

        int counter = 0 ;
        while(num != 0){
            num /= 10;
            counter++;
        }

        System.out.println("The number of digits is: " + counter);


    }
}
