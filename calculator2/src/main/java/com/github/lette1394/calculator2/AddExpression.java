package com.github.lette1394.calculator2;

public class AddExpression extends TwoOperand {
  public AddExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public long evaluate() {
    return Math.addExact(left.evaluate(), right.evaluate());
  }
}
