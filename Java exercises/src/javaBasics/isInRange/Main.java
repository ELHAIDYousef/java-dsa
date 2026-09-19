package javaBasics.isInRange;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number to test: ");
        int a = sc.nextInt();
        System.out.print("Enter the lower value: ");
        int b = sc.nextInt();
        System.out.print("Enter the highest value: ");
        int c = sc.nextInt();

        sc.close();

        System.out.println( a + (isInRange(a, b, c)? " is": " is not" + " in range of [ "+ b + " , " + b + " ]." ));
    }

    static boolean isInRange(int num, int low, int high){
        return num >= low && num <= high;
    }
}

