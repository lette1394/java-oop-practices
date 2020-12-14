package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

public abstract class NumberOperator extends TwoOperand {
  public NumberOperator(Expression left, Expression right) {
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
