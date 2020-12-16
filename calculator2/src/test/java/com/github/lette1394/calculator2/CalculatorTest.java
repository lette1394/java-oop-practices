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
    assertThat(subjectString("0"), is("0"));
    assertThat(subjectDouble("0"), is(0.0));
    assertThat(subjectLong("0"), is(0L));
    assertThat(subjectLongExact("0"), is(0L));

    assertThat(subjectLong("1"), is(1L));
    // FIXME (jaeeun) 2020-12-17: pass test
//    assertThat(subjectLong("+1"), is(+1L));
    assertThat(subjectLong("-1"), is(-1L));
  }

  @Test
  void two_operand() {
    assertThat(subjectLong("10 + 5"), is(15L));
    assertThat(subjectLong("10 - 5"), is(5L));
    assertThat(subjectLong("10 * 5"), is(50L));
    assertThat(subjectLong("10 / 5"), is(2L));
  }

  @Test
  void three_operand() {
    assertThat(subjectLong("2 + 3 + 6"), is(11L));
    assertThat(subjectLong("2 - 3 - 6"), is(-7L));
    assertThat(subjectLong("2 * 3 * 6"), is(36L));

    assertThat(subjectLong("2 + 3 - 6"), is(-1L));
    assertThat(subjectLong("2 - 3 + 6"), is(5L));
    assertThat(subjectLong("2 * 3 + 6"), is(12L));
    assertThat(subjectLong("2 * 3 - 6"), is(0L));
  }

  @Test
  void three_operand_divide() {
    assertThat(subjectLong("2 * 3 / 6"), is(1L));
    assertThat(subjectString("2 * 3 / 6"), is("1"));
    assertThat(subjectString("2 / 3 / 6"), is("0.1111111111111111"));
    assertThat(subjectString("2 / 3 + 6"), is("6.666666666666667"));
    assertThat(subjectString("2 / 3 - 6"), is("-5.333333333333333"));

    // FIXME (jaeeun) 2020-12-17: string으로 뽑으면 "4"라고 나와야 하는거 아니냐
    assertThat(subjectString("2 / 3 * 6"), is("4.0"));
    assertThat(subjectDouble("2 / 3 * 6"), is(4.0));
    assertThat(subjectLong("2 / 3 * 6"), is(4L));
  }

  @Test
  void four_operand() {
    assertThat(subjectLong("2 - 3 + 6 - 5"), is(0L));
    assertThat(subjectLong("2 - 3 + 6 - 1"), is(4L));
  }

  @Test
  void formatting() {
    assertThat(subjectLong("99-3"), is(96L));
    assertThat(subjectLong("99-3+3"), is(99L));
    assertThat(subjectLong("99-3+3*99"), is(393L));
  }

  @Test
  void formatting_minus_prefix() {
    assertThat(subjectLong("99+112 - 1823+34+3"), is(-1575L));
    assertThat(subjectLong("99+112 -1823+34+3"), is(-1575L));
    assertThat(subjectLong("99+112- 1823+34+3"), is(-1575L));
  }

  @Test
  void overflow() {
    assertThat(subjectString("9223372036854775807 + 1"), is("9223372036854775808"));
    assertThat(subjectString("9223372036854775808 + 1"), is("9223372036854775809"));
    assertThat(subjectString("-9223372036854775808 - 1"), is("-9223372036854775809"));
    assertThat(
      subjectString("9223372036854775808 * 12980321798142"),
      is("119722337102359424610218840948736"));
    assertThat(
      subjectString("-9223372036854775808 * 12980321798142"),
      is("-119722337102359424610218840948736"));

    assertThrows(
      ArithmeticException.class,
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