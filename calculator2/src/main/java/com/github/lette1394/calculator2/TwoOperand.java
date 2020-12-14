package com.github.lette1394.calculator2;

public abstract class TwoOperand implements Expression {
  protected final Expression leftExpression;
  protected final Expression rightExpression;

  private Result left;
  private Result right;

  public TwoOperand(Expression left, Expression right) {
    this.leftExpression = left;
    this.rightExpression = right;
  }

  private static boolean isDouble(Result result) {
    return result.asLong() != result.asDouble();
  }

  protected boolean operandsInDouble() {
    return isDouble(left()) || isDouble(right());
  }

  protected Result left() {
    if (left == null) {
      return leftExpression.evaluate();
    }
    return left;
  }

  protected Result right() {
    if (right == null) {
      return rightExpression.evaluate();
    }
    return right;
  }
}