package javaBasics.ppcm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        int n1 = sc.nextInt();
        int a = n1;
        System.out.print("Enter the 2nd number: ");
        int n2 = sc.nextInt();
        int b = n2;

        sc.close();

        while(n2 != 0){
            int r = n1 % n2;
            n1 = n2;
            n2 = r;
        }

        System.out.println("The LCM is: " + (a * b)/ n1 );
    }
}
