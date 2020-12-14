package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;
import static java.lang.Math.subtractExact;

public class SubtractExpression extends TwoOperand {
  public SubtractExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public Result evaluate() {
    if (operandsInDouble()) {
      return of(left().asDouble() - right().asDouble());
    }
    return of(subtractExact(left().asLongExact(), right().asLongExact()));
  }
}
