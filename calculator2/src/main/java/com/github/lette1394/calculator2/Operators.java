package com.github.lette1394.calculator2;

import java.math.BigInteger;

public class Operators {
  public static Operator add() {
    return (left, right) -> fallback(
      new AddExpression(left, right),
      () -> new BigIntegerExpression(
        new BigInteger(String.valueOf(left.evaluate())).add(new BigInteger(String.valueOf(right.evaluate())))).evaluate())
      .apply(left, right);
  }

  public static Operator subtract() {
    return (left, right) -> new SubtractExpression(left, right);
  }

  public static Operator multiply() {
    return (left, right) -> new MultiplyExpression(left, right);
  }

  public static Operator divide() {
    return (left, right) -> new DivideExpression(left, right);
  }

  public static Operator fallback(Expression operator, Expression fallback) {
    return (left, right) -> new FallbackExpression(operator, fallback);
  }
}