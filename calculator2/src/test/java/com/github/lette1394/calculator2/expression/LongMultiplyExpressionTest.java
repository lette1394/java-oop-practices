package com.github.lette1394.calculator2.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LongMultiplyExpressionTest {

  @Test
  void subtract() {
    assertThat(subjectLong("3*2"), is(6L));
  }

  @Test
  void formatted() {
    assertThat(subjectLong("3 * 2"), is(6L));
    assertThat(subjectLong("3 *2"), is(6L));
    assertThat(subjectLong("3* 2"), is(6L));
    assertThat(subjectLong("3*    2"), is(6L));
    assertThat(subjectLong("3  *     2"), is(6L));
    assertThat(subjectLong("    3  *     2"), is(6L));
    assertThat(subjectLong("    3  *     2    "), is(6L));
    assertThat(subjectLong("3  *     2    "), is(6L));
    assertThat(subjectLong("  3*2     "), is(6L));
  }

  @Test
  void unsupported() {
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("(3 *2)"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("( 3 *2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("(3 *2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("3 *2 )"));
    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("3 *2 )"));

    assertThrows(UnsupportedExpressionException.class, () -> subjectLong("1 * 2 * 4"));
  }

  private long subjectLong(String expression) {
    return new LongMultiplyExpression(expression).evaluate().asLongExact();
  }
}