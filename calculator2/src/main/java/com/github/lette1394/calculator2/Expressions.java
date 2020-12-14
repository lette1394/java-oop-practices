package com.github.lette1394.calculator2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Supplier;

public final class Expressions {
  public static Expression of(String value) {
    return fallback(new StringExpression(value), () -> of(new BigInteger(value)));
  }

  public static Expression of(long value) {
    return fallback(new NumberExpression(value), () -> of(new BigInteger(Long.toString(value))));
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

  public static Expression fallback(Expression expression, Supplier<Expression> fallback) {
    return new FallbackExpression(
      e -> e instanceof NumberFormatException,
      expression,
      fallback);
  }
}
