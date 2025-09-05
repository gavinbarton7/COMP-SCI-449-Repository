package org.example;

public class Calculator {
    // method for addition
    public int add(int number1, int number2) {
        //create variable for result of calculation
        int result = 0;

        //perform calculation
        result = number1 + number2;

        //return result of the calculation
        return result;
    }

    // method for subtraction
    public int subtract(int number1, int number2) {
        //create variable for result of calculation
        int result = 0;

        //perform calculation
        result = number1 - number2;

        //return result of the calculation
        return result;
    }

    // method for multiplication
    public int multiply(int number1, int number2) {
        //create variable for result of calculation
        int result = 0;

        //perform calculation
        result = number1 * number2;

        //return result of the calculation
        return result;
    }

    // method for division (if number1 is not divisible by number2, returns results without
    // remainder
    public int divide(int number1, int number2) {
        //create variable for result of calculation
        int result = 0;

        //perform calculation
        result = number1 / number2;

        //return result of the calculation
        return result;
    }
}
