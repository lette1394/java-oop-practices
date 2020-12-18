package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LongAddExpressionTest {

  @Test
  void add() {
    assertThat(subjectLong("1+2"), is(3L));
  }

  @Test
  void formatted() {
    assertThat(subjectLong("1 + 2"), is(3L));
    assertThat(subjectLong("1 +2"), is(3L));
    assertThat(subjectLong("1+ 2"), is(3L));
    assertThat(subjectLong("1+    2"), is(3L));
    assertThat(subjectLong("1  +     2"), is(3L));
    assertThat(subjectLong("    1  +     2"), is(3L));
    assertThat(subjectLong("    1  +     2    "), is(3L));
    assertThat(subjectLong("1  +     2    "), is(3L));
    assertThat(subjectLong("  1+2     "), is(3L));
  }

  @Test
  void unsupported() {
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("(1 +2)"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("( 1 +2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("(1 +2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("1 +2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("1 +2 )"));

    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("1 + 2 + 4"));
  }

  private long subjectLong(String expression) {
    return new LongAddExpression(expression).evaluate().asLongExact();
  }
}