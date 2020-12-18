package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

class FirstMatchedExpressionTest {

  @Test
  void matched() {
    assertThat(subjectLong("1 + 4"), is(5L));
  }

  @Test
  void unmatched() {
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("2 * 4"));
  }

  private long subjectLong(String expression) {
    final List<Supplier<Expression>> suppliers = List.of(
      supportAdd(expression),
      supportSubtract(expression));
    return new FirstMatchedExpression(suppliers).evaluate().asLongExact();
  }

  private Supplier<Expression> supportSubtract(String expression) {
    return () -> new LongSubtractExpression(expression);
  }

  private Supplier<Expression> supportAdd(String expression) {
    return () -> new LongAddExpression(expression);
  }
}