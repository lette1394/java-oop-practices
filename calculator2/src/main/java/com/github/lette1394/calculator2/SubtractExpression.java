package com.github.lette1394.calculator2;

public class SubtractExpression extends TwoOperand {
  public SubtractExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public long evaluate() {
    return left.evaluate() - right.evaluate();
  }
}
