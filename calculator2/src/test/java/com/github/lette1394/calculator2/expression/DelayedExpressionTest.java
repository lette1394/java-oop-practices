package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.google.common.base.Stopwatch;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

public class DelayedExpressionTest {
  private static final Duration DELAY = Duration.ofMillis(10);

  // TODO: 많이 쓰게 되면 Junit5 Extension 으로 뽑아내자
  private Stopwatch stopwatch;

  @Test
  void delayed() {
    assertThat(assertDoesNotThrow(subject("12345", DELAY)), is(12345L));
  }

  @BeforeEach
  void beforeEach() {
    stopwatch = Stopwatch.createStarted();
  }

  @AfterEach
  void afterEach() {
    stopwatch.stop();
    assertThat(stopwatch.elapsed(), greaterThanOrEqualTo(DELAY));
  }

  private ThrowingSupplier<Long> subject(String expression, Duration duration) {
    return () -> new DelayedExpression(NumericExpressionFactory.INSTANCE.of(expression), duration)
      .evaluate().asLongExact();
  }
}
