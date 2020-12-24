package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.lette1394.calculator2.result.Result;
import org.junit.jupiter.api.Test;

class DivideExpressionTest {

  // TODO:
  //  3/2*2 = 3L, not 3.0D

  @Test
  void divide() {
    assertThat(subjectLong("3/2"), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));
  }

  @Test
  void formatted() {
    assertThat(subjectLong("3 / 2"), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("3 /2"), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("3/ 2"), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("3/    2"), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("3  /     2"), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("    3  /     2"), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("    3  /     2    "), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("3  /     2    "), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));

    assertThat(subjectLong("  3/2     "), is(1L));
    assertThat(subjectDouble("3/2"), is(1.5));
  }

  @Test
  void unsupported() {
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("(3 /2)"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("( 3 /2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("(3 /2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("3 /2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("3 /2 )"));

    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("1 / 2 / 4"));
  }

  @Test
  void divide_by_zero() {
    assertThrows(DivideByZeroException.class, () -> subjectLong("1 / 0"));
    assertThrows(DivideByZeroException.class, () -> subjectDouble("1 / 0"));
  }

  private long subjectLong(String expression) {
    return subject(expression).asLongExact();
  }

  private double subjectDouble(String expression) {
    return subject(expression).asDouble();
  }

  private Result subject(String expression) {
    return new DivideExpression(expression).evaluate();
  }
}