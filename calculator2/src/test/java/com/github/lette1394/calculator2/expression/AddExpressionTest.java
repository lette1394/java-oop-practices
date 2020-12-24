package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.lette1394.calculator2.result.Result;
import org.junit.jupiter.api.Test;

class AddExpressionTest {

  @Test
  void add() {
    assertThat(subjectLong("1+2"), is(3L));
    assertThat(subjectLong("1.0+2"), is(3L));
    assertThat(subjectDouble("1.0+2"), is(3.0));

    assertThat(subjectLong("1.7+2"), is(3L));
    assertThat(subjectDouble("1.7+2"), is(3.7));
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

  private Result subject(String expression) {
    return new AddExpression(expression).evaluate();
  }

  private long subjectLong(String expression) {
    return subject(expression).asLongExact();
  }

  private double subjectDouble(String expression) {
    return subject(expression).asDouble();
  }
}