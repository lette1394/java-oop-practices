package com.github.lette1394.calculator2;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TypeExpressionFactory implements ExpressionFactory {
  public static final ExpressionFactory INSTANCE = new TypeExpressionFactory();

  private TypeExpressionFactory() {
  }

  @Override
  public Expression of(String value) {
    return new StringExpression(value);
  }

  @Override
  public Expression of(long value) {
    return new NumberExpression(value);
  }

  @Override
  public Expression of(double value) {
    return new NumberExpression(value);
  }

  @Override
  public Expression of(BigInteger value) {
    return new BigIntegerExpression(value);
  }

  @Override
  public Expression of(BigDecimal value) {
    return new BigDecimalExpression(value);
  }
}
