package com.github.lette1394.calculator2.expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathTypeExpressionFactory implements ExpressionFactory {
  public static final ExpressionFactory INSTANCE = new MathTypeExpressionFactory();

  private MathTypeExpressionFactory() {
  }

  @Override
  public Expression of(String value) {
    return new StringExpression(value);
  }

  @Override
  public Expression of(long value) {
    return new NumericExpression(value);
  }

  @Override
  public Expression of(double value) {
    return new NumericExpression(value);
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