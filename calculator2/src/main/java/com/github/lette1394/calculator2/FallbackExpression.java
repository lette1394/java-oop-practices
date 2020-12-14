package com.github.lette1394.calculator2;

import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FallbackExpression implements Expression {
  private final Predicate<Throwable> fallbackTriggerCondition;
  private final Expression expression;
  private final Supplier<Expression> fallback;

  @Override
  public Result evaluate() {
    try {
      return expression.evaluate();
    } catch (Throwable e) {
      if (fallbackTriggerCondition.test(e)) {
        return fallback.get().evaluate();
      }
      throw e;
    }
  }
}
