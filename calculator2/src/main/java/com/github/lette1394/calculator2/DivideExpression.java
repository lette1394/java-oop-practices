package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

public class DivideExpression extends TwoOperand {
  public DivideExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public Result evaluate() {
    if (operandsInDouble()) {
      return of(left().asDouble() / right().asDouble());
    }
    return of(left().asLongExact() / right().asLongExact());
  }
}
