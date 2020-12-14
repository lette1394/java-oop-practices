package com.github.lette1394.calculator2;

public class CachedExpression implements Expression {
  private final Expression expression;
  private Result result;

  public CachedExpression(Expression expression) {
    this.expression = expression;
  }

  @Override
  public Result evaluate() {
    if (result == null) {
      return result = expression.evaluate();
    }
    return result;
  }
}
