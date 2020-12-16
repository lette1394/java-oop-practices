package com.github.lette1394.calculator2.expression;

import static java.lang.String.format;

import com.github.lette1394.calculator2.result.Result;

class CachedExpression implements Expression {
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

  @Override
  public String toString() {
    return format("cached: [%s]", expression.toString());
  }
}
