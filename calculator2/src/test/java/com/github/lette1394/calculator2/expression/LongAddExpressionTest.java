package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class LongAddExpressionTest {

  @Test
  void add() {
    assertThat(subjectLong("1+2"), is(3L));
  }

  private long subjectLong(String expression) {
    return new LongAddExpression(expression).evaluate().asLongExact();
  }
}