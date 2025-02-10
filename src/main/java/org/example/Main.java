package org.example;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression (or type 'q' to quit):");

        Calculator calculator = new Calculator();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) break;

            try {
                double result = calculator.calculate(input);
                System.out.println("Answer: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}