package com.github.lette1394.calculator2.expression;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class PreemptiveTimeoutExpressionTest {

  @Test
  void timeout() {
    final Expression expression = new DelayedExpression(
      NumericExpressionFactory.INSTANCE.of("12345"), Duration.ofMillis(500));

    assertThrows(EvaluationTimeoutException.class,
      () -> new PreemptiveTimeoutExpression(expression, Duration.ofMillis(500)).evaluate());
  }
}