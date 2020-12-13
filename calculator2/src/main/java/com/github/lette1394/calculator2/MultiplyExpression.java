package com.github.lette1394.calculator2;

public class MultiplyExpression extends TwoOperand {
  public MultiplyExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public long evaluate() {
    return left.evaluate() * right.evaluate();
  }
}
