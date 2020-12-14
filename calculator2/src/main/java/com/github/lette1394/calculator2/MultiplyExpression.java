package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;
import static java.lang.Math.multiplyExact;

public class MultiplyExpression extends TwoOperand {
  public MultiplyExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public Result evaluate() {
    if (operandsInDouble()) {
      return of(left().asDouble() * right().asDouble());
    }
    return of(multiplyExact(left().asLongExact(), right().asLongExact()));
  }
}
