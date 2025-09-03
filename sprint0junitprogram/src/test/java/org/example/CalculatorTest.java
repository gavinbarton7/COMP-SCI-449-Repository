package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

  @Test
  public void testAdd1() {
    Calculator calculator = new Calculator();
    int result = calculator.add(4, 9);
    assertEquals(13, result);
  }

  @Test
  public void testAdd2() {
    Calculator calculator = new Calculator();
    int result = calculator.add(7, 19);
    assertEquals(26, result);
  }

  @Test
  public void testSubtract1() {
    Calculator calculator = new Calculator();
    int result = calculator.subtract(23, 15);
    assertEquals(8, result);
  }

  @Test
  public void testSubtract2() {
    Calculator calculator = new Calculator();
    int result = calculator.subtract(19, 4);
    assertEquals(15, result);
  }

  @Test
  public void testMultiply1() {
    Calculator calculator = new Calculator();
    int result = calculator.multiply(7, 5);
    assertEquals(35, result);
  }

  @Test
  public void testMultiply2() {
    Calculator calculator = new Calculator();
    int result = calculator.multiply(8, 3);
    assertEquals(24, result);
  }

  @Test
  public void testDivide1() {
    Calculator calculator = new Calculator();
    int result = calculator.divide(15, 3);
    assertEquals(5, result);
  }

  @Test
  public void testDivide2() {
    Calculator calculator = new Calculator();
    int result = calculator.divide(27, 9);
    assertEquals(3, result);
  }

}