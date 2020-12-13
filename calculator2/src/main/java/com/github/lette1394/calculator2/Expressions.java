package com.github.lette1394.calculator2;

public final class Expressions {
  public static Expression of(String value) {
    return new StringExpression(value);
  }

  public static Expression of(long value) {
    return new LongExpression(value);
  }

  public static Expression add(Expression left, Expression right) {
    return new AddExpression(left, right);
  }

  public static Expression parsing(String expression) {
    return new ParsingExpression(expression);
  }
}
