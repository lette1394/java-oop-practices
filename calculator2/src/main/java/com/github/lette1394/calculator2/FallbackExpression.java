package com.github.lette1394.calculator2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FallbackExpression implements Expression {
  private final Expression expression;
  private final Expression fallback;

  @Override
  public Result evaluate() {
    try {
      return expression.evaluate();
    } catch (Exception e) {
      return fallback.evaluate();
    }
  }
}
