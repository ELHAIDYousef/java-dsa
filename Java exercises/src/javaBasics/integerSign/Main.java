package javaBasics.integerSign;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num = sc.nextInt();
        sc.close();

        if (num >= 0)
            if(num > 0 ) System.out.println(num + " is positive.");
            else System.out.println(num + " is null.");
        else System.out.println(num + " is negative.");
    }
}
