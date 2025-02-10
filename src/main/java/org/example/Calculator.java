package org.example;

import java.util.Stack;

public class Calculator {
    public Stack<Double> numbers;
    public Stack<Character> operators;

    Calculator(){
        this.numbers = new Stack<>();
        this.operators = new Stack<>();
    }

    public int getIntValue(char ch){
        return Character.getNumericValue(ch);
    }

    public double addition(double num1, double num2){
        return num1 + num2;
    }

    public double subtraction(double num1, double num2){
        return num1 - num2;
    }

    public  double multiplication(double num1, double num2){
        return num1 * num2;
    }

    public  double division(double num1, double num2){
        return num1/num2;
    }

    public double calculate(String expression) throws Exception {
        numbers.clear();
        operators.clear();

        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == ' ') continue;

                if (Character.isDigit(c) || c == '.') {
                    StringBuilder sb = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        sb.append(expression.charAt(i++));
                    }
                    i--;
                    numbers.push(Double.parseDouble(sb.toString()));
                } else if (c == '(') {
                    operators.push(c);
                } else if (c == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                    operators.pop();
                } else if (isValidOperator(c)) {
                    while (!operators.isEmpty() && importance(operators.peek()) >= importance(c)) {
                        numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                    }
                    operators.push(c);
                }

            }

            while (!operators.isEmpty()) {
                numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
            }
            return numbers.pop();
        } catch (Exception e){
            throw new Exception("Invalid entry.");
        }
    }

    public boolean isValidOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public int importance(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    public double applyOperation(char op, double b, double a) {
        return switch (op) {
            case '+' -> this.addition(a, b);
            case '-' -> this.subtraction(a, b);
            case '*' -> this.multiplication(a, b);
            case '/' -> {
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                yield this.division(a, b);
            }
            default -> 0;
        };
    }

}
