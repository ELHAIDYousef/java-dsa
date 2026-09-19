package javaBasics.twoNumbersOperations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int num1 = sc.nextInt();
        System.out.print("\n Enter the second number: ");
        int num2 = sc.nextInt();

        System.out.println("""
                
                Choose an operation:
                1. Addition
                2. Subtraction
                3. Multiplication
                4. Division
                5. Remainder
                """);

        System.out.print("Choice: ");
        int choice = sc.nextInt();

        sc.close();

        switch (choice){
            case 1 -> System.out.println("Result: " + (num1 + num2));
            case 2 -> System.out.println("Result: " + (num1 - num2));
            case 3 -> System.out.println("Result: " + (num1 * num2));
            case 4 ->{
                if (num2 == 0)
                    System.out.println("Error: Division by Zero is impossible!!!");
                else
                    System.out.println("Result: " + (num1 / num2));

            }
            case 5 ->{
                if (num2 == 0)
                    System.out.println("Error: Division by Zero is impossible!!!");
                else
                    System.out.println("Result: " + (num1 % num2));
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}
