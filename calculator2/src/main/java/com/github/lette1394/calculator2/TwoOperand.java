package com.github.lette1394.calculator2;

public abstract class TwoOperand implements Expression {
  protected final Expression left;
  protected final Expression right;

  public TwoOperand(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }
}
