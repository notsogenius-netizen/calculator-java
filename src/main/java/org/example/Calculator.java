package org.example;

public class Calculator {
    public String addition;
    public String subtraction;
    public String multiplication;
    public String division;

    Calculator(){
        this.addition = "+";
        this.subtraction = "-";
        this.multiplication = "*";
        this.division = "/";
    }

    public int getIntValue(char ch){
        return Character.getNumericValue(ch);
    }

    public int addition(int num1, int num2){
        return num1 + num2;
    }
}
