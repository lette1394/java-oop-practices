package com.github.lette1394.calculator2.expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CachedExpressionFactory implements ExpressionFactory {
  private final ExpressionFactory delegate;

  public CachedExpressionFactory(ExpressionFactory delegate) {
    this.delegate = delegate;
  }

  @Override
  public Expression of(String value) {
    return cache(delegate.of(value));
  }

  @Override
  public Expression of(long value) {
    return cache(delegate.of(value));
  }

  @Override
  public Expression of(double value) {
    return cache(delegate.of(value));
  }

  @Override
  public Expression of(BigInteger value) {
    return cache(delegate.of(value));
  }

  @Override
  public Expression of(BigDecimal value) {
    return cache(delegate.of(value));
  }

  private Expression cache(Expression expression) {
    return new CachedExpression(expression);
  }
}
