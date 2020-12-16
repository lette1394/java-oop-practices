package com.github.lette1394.calculator2.expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumericExpressionFactory implements ExpressionFactory {
  public static final ExpressionFactory INSTANCE = new NumericExpressionFactory();

  private NumericExpressionFactory() {
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
    throw new UnsupportedOperationException("NumericExpressionFactory does not support BigInteger");
  }

  @Override
  public Expression of(BigDecimal value) {
    throw new UnsupportedOperationException("NumericExpressionFactory does not support BigDecimal");
  }
}
