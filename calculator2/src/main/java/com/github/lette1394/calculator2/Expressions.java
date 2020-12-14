package com.github.lette1394.calculator2;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class Expressions {
  public static Expression of(String value) {
    return new StringExpression(value);
  }

  public static Expression of(long value) {
    return new NumberExpression(value);
  }

  public static Expression of(double value) {
    return new NumberExpression(value);
  }

  public static Expression of(BigInteger value) {
    return new BigIntegerExpression(value);
  }

  public static Expression of(BigDecimal value) {
    return new BigDecimalExpression(value);
  }

  public static Expression cache(Expression expression) {
    return new CachedExpression(expression);
  }

  public static Expression parse(String expression) {
    return new PriorityParsingExpression(expression);
  }
}
