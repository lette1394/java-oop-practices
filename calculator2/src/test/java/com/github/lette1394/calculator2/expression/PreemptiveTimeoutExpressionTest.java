package com.github.lette1394.calculator2.expression;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.lette1394.calculator2.result.Result;
import java.time.Duration;
import org.junit.jupiter.api.Test;

class PreemptiveTimeoutExpressionTest {
  private static final Duration TIMEOUT = Duration.ofMillis(500);

  @Test
  void timeout() {
    final Expression expression = new DelayedExpression(
      NumericExpressionFactory.INSTANCE.of("12345"), TIMEOUT);

    assertThrows(EvaluationTimeoutException.class, () -> subject(expression));
  }

  private Result subject(Expression expression) {
    return new PreemptiveTimeoutExpression(expression, TIMEOUT).evaluate();
  }
}