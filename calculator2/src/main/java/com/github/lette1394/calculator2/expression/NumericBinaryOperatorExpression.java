package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.Results.of;

import com.github.lette1394.calculator2.result.Result;

abstract class NumericBinaryOperatorExpression extends TwoOperandExpression {
  public NumericBinaryOperatorExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public Result evaluate() {
    if (operandsInDouble()) {
      return of(whenUseDouble(left().asDouble(), right().asDouble()));
    }
    return of(whenUseLong(left().asLongExact(), right().asLongExact()));
  }

  protected abstract Number whenUseLong(long left, long right);

  protected abstract Number whenUseDouble(double left, double right);
}
