package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Results.of;

import java.math.BigInteger;

public abstract class BigIntegerTwoOperand implements Expression {
  protected final Expression leftExpression;
  protected final Expression rightExpression;

  public BigIntegerTwoOperand(Expression left, Expression right) {
    this.leftExpression = left;
    this.rightExpression = right;
  }

  protected BigInteger left() {
    return new BigInteger(leftExpression.evaluate().asString());
  }

  protected BigInteger right() {
    return new BigInteger(rightExpression.evaluate().asString());
  }

  @Override
  public Result evaluate() {
    return of(handle(left(), right()));
  }

  protected abstract BigInteger handle(BigInteger left, BigInteger right);
}
