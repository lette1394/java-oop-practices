package com.github.lette1394.calculator2;

import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FallbackExpression implements Expression {
  private final Predicate<Throwable> condition;
  private final Expression expression;
  private final Expression fallback;

  @Override
  public Result evaluate() {
    try {
      return expression.evaluate();
    } catch (Throwable e) {
      if (condition.test(e)) {
        return fallback.evaluate();
      }
      throw e;
    }
  }
}
