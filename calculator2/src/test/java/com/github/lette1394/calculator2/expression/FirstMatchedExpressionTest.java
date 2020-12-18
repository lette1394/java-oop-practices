package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    assertThat(subjectLong("2 * 4"), is(8L));
  }

  private long subjectLong(String expression) {
    final List<Supplier<Expression>> suppliers = List.of(
      () -> new LongAddExpression(expression),
      () -> new LongSubtractExpression(expression));
    return new FirstMatchedExpression(suppliers).evaluate().asLongExact();
  }

}