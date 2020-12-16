package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class PreemptiveTimeoutExpressionTest {
  private static final Duration TIMEOUT = Duration.ofMillis(100);

  @Test
  void timeout() {
    assertThrows(EvaluationTimeoutException.class,
      () -> subject(delayed("12345", TIMEOUT), TIMEOUT));
  }

  @Test
  void no_timeout() {
    assertThat(subject(zeroDelayed("12345"), TIMEOUT), is(12345L));
  }

  private Expression zeroDelayed(String stringExpression) {
    return new DelayedExpression(NumericExpressionFactory.INSTANCE.of(stringExpression),
      Duration.ZERO);
  }

  private Expression delayed(String stringExpression, Duration timeout) {
    return new DelayedExpression(NumericExpressionFactory.INSTANCE.of(stringExpression), timeout);
  }

  private long subject(Expression expression, Duration timeout) {
    return new PreemptiveTimeoutExpression(expression, timeout).evaluate().asLongExact();
  }
}