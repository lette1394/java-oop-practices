package com.github.lette1394.calculator2;

public class Operators {
  public static Operator add() {
    return (left, right) -> new AddExpression(left, right);
  }

  public static Operator subtract() {
    return (left, right) -> new SubtractExpression(left, right);
  }

  public static Operator multiply() {
    return (left, right) -> new MultiplyExpression(left, right);
  }
}
