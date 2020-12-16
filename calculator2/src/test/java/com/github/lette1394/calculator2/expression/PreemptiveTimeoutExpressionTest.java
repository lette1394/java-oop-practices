package com.github.lette1394.calculator2.expression;

import static java.time.Duration.ofMillis;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class PreemptiveTimeoutExpressionTest {

  @Test
  void timeout() {
    assertThrows(EvaluationTimeoutException.class,
      () -> subject(delayed("123", ofMillis(100)), timeoutMillis(10)));
  }

  @Test
  void no_timeout() {
    assertThat(subject(zeroDelayed("123"), timeoutMillis(1000)), is(123L));
  }

  private Duration timeoutMillis(long value) {
    return ofMillis(value);
  }

  private Expression zeroDelayed(String stringExpression) {
    return new DelayedExpression(
      NumericExpressionFactory.INSTANCE.of(stringExpression),
      Duration.ZERO);
  }

  private Expression delayed(String stringExpression, Duration timeout) {
    return new DelayedExpression(NumericExpressionFactory.INSTANCE.of(stringExpression), timeout);
  }

  private long subject(Expression expression, Duration timeout) {
    return new PreemptiveTimeoutExpression(expression, timeout).evaluate().asLongExact();
  }
}