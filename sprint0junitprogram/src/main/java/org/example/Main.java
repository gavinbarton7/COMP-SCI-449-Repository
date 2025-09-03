package org.example;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // numbers user provides for add, subtract, multiple, and divide
    int userInt1 = 0;
    int userInt2 = 0;

    //create instance of the class "calculator"
    Calculator calculator = new Calculator();

    //list options for calculator
    System.out.println("Calculator options:");
    System.out.println("  1-Add");
    System.out.println("  2-Subtract");
    System.out.println("  3-Multiply");
    System.out.println("  4-Divide");
    System.out.println("  5-Exit program");
    System.out.println("");

    //gets user input for which operation they want to perform
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please choose an option 1-5:-->");
    int userSelectOperation = scanner.nextInt();


    /*checks to see if user input was between 1 and 4. If it is not, then
    the program asks the user to input another input for the operation
    until the user inputs a number between 1 and 4.*/
    while (userSelectOperation > 5 || userSelectOperation < 1) {
      System.out.print("Please choose an option 1-5:-->");
      userSelectOperation = scanner.nextInt();
    }

    /*defines a variable to represent a variable type, initializing it to
    0 then asks the user whether they want to use ints or doubles in the
    operation*/
    int userSelectVariableType = 0;

    while (userSelectOperation != 5) {

      //The program gets two values for the operation from user input
      System.out.println("Enter first value-->");
      userInt1 = scanner.nextInt();
      System.out.println("Enter second value-->");
      userInt2 = scanner.nextInt();

      /* if the user chooses to use the operation "add",
      the program accesses the "add" mehtod and calculates the sum*/
      if (userSelectOperation == 1){
        int calculationResult = calculator.add(userInt1, userInt2);
        // print the return value of the instance "calculationResultInt"
        System.out.println("Result: " + calculationResult);
      }


      /* if the user chooses to use the operation "subtract", the program accesses the "subtract"
      method for doubles in the Calculator class in Calculator.java and uses the two values from
      the user input as the values for the methods parameters */
      else if (userSelectOperation == 2) {
        // create variable for instance of "subtract" method
        int calculationResult = calculator.subtract(userInt1, userInt2);
        //print the return value of the instance "calculationResultInt"
        System.out.println("Result: " + calculationResult);
      }

      /* if the user chooses to use the operation "multiply", the program accesses the "multiply"
      multiply method in the Calculator class in Calculator.java and uses the two values from
      the user input as the values for the methods parameters */
      else if (userSelectOperation == 3) {
        // create variable for instance of "multiply" method
        int calculationResultInt = calculator.multiply(userInt1, userInt2);
        //print the return value of the instance "calculationResultInt"
        System.out.println("Result: " + calculationResultInt);
      }

      /* if the user to use the operation "divide", the program accesses the "divide" method for
      doubles in the Calculator class in Calculator.java and uses the two values from the user
      input as the values for the method's parameters */
      else if (userSelectOperation == 4) {
        // create variable for instance of "divide" method
        int calculationResultInt = calculator.divide(userInt1, userInt2);
        //print the return value of the instance "calculationResultInt"
        System.out.println("Result: " + calculationResultInt);
      }

      System.out.println("");
      System.out.println("Please choose an option 1-5:-->");
      userSelectOperation = scanner.nextInt();

      /*checks to see if user input was between 1 and 4. If it is not, then
      the program asks the user to input another input for the operation
      until the user inputs a number between 1 and 4.*/
      while (userSelectOperation > 5 || userSelectOperation < 1) {
        System.out.print("Please choose an option 1-5:-->");
        userSelectOperation = scanner.nextInt();
      }
    }
    scanner.close();
  }
}