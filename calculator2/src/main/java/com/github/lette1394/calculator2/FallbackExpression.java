package com.github.lette1394.calculator2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FallbackExpression implements Expression {
  private final Class<? extends Throwable> excludeThrowableType;
  private final Expression expression;
  private final Expression fallback;

  @Override
  public Result evaluate() {
    try {
      return expression.evaluate();
    } catch (Throwable e) {
      if (!excludeThrowableType.isInstance(e)) {
        return fallback.evaluate();
      }
      throw e;
    }
  }
}
