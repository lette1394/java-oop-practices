package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.TestFixtures.subject;
import static com.github.lette1394.calculator2.TestFixtures.subjectDouble;
import static com.github.lette1394.calculator2.TestFixtures.subjectLong;
import static com.github.lette1394.calculator2.TestFixtures.subjectLongExact;
import static com.github.lette1394.calculator2.TestFixtures.subjectString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.lette1394.calculator2.expression.DivideByZeroException;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  @Test
  void one_operand() {
    assertThat(subjectLong("0"), is(0L));
    assertThat(subjectLong("1"), is(1L));
    assertThat(subjectLong("-1"), is(-1L));
  }

  @Test
  void two() {
    assertThat(subjectLong("10 - 7"), is(3L));
  }

  @Test
  void three() {
    assertThat(subjectLong("2 + 3 + 6"), is(11L));
    assertThat(subjectLong("2 - 3 - 6"), is(-7L));
  }

  @Test
  void four() {
    assertThat(subjectLong("2 - 3 + 6 - 5"), is(0L));
    assertThat(subjectLong("2 - 3 + 6 - 1"), is(4L));
  }

  @Test
  void minusOperator() {
    assertThat(subjectLong("99-3"), is(96L));
    assertThat(subjectLong("99-3+3"), is(99L));
    assertThat(subjectLong("99-3+3-99"), is(0L));
    assertThat(subjectLong("99+112 - 1823+34+3"), is(-1575L));
    assertThat(subjectLong("99+112 -1823+34+3"), is(-1575L));
    assertThat(subjectLong("99+112- 1823+34+3"), is(-1575L));
  }

  @Test
  void multiply() {
    assertThat(subjectLong("2 * 6"), is(12L));
    assertThat(subjectLong("2 * 6 - 2"), is(10L));
    assertThat(subjectLong("2 - 6 * 2"), is(-10L));
    assertThat(subjectLong("2 * 3 * 4 - 6 + 7 * 8"), is(74L));
  }

  @Test
  void overflow() {
    assertThat(subjectString("9223372036854775807 + 1"), is("9223372036854775808"));
    assertThat(subjectString("9223372036854775808 + 1"), is("9223372036854775809"));
    assertThat(subjectString("-9223372036854775808 - 1"), is("-9223372036854775809"));
    assertThat(subjectString("9223372036854775808 * 12980321798142"),
      is("119722337102359424610218840948736"));
    assertThat(subjectString("-9223372036854775808 * 12980321798142"),
      is("-119722337102359424610218840948736"));

    assertThrows(ArithmeticException.class,
      () -> subjectLongExact("-9223372036854775808 * 12980321798142"));
  }

  @Test
  void divide() {
    assertThat(subjectLong("6 / 3"), is(2L));
    assertThat(subjectDouble("6 / 3"), is(2.0));
  }

  @Test
  void divideByZero() {
    assertThrows(DivideByZeroException.class, () -> subject("1 / 0"));
    assertThrows(DivideByZeroException.class, () -> subject("5*6+6/0-10"));
    assertThrows(DivideByZeroException.class,
      () -> subject("119722337102359424610218840948736/0-10"));
  }
}