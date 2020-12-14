package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;
import static java.lang.Math.addExact;

public class AddExpression extends TwoOperand {
  public AddExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public Result evaluate() {
    if (operandsInDouble()) {
      return of(left().asDouble() + right().asDouble());
    }
    return of(addExact(left().asLongExact(), right().asLongExact()));
  }
}
