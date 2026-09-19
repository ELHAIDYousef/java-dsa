package javaBasics.pgcd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 1st number: ");
        int n1 = sc.nextInt();
        System.out.println("Enter the 2nd number: ");
        int n2 = sc.nextInt();

        sc.close();

        while (n2 != 0 ){
            int r = n1 % n2 ;
            n1 = n2 ;
            n2 = r;
        }
        System.out.println(" The GCD is :" + n1);

    }
}
