package com.github.lette1394.calculator2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddExpression implements Expression {
  private final Expression left;
  private final Expression right;

  @Override
  public long evaluate() {
    return left.evaluate() + right.evaluate();
  }
}
