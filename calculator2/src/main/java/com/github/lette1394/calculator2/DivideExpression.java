package com.github.lette1394.calculator2;

public class DivideExpression extends TwoOperand {
  public DivideExpression(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public long evaluate() {
    return left.evaluate() / right.evaluate();
  }
}
