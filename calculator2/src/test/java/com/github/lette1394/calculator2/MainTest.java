package com.github.lette1394.calculator2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class MainTest {
  @Test
  void one() {
    assertThat(subject("2 + 3"), is(5L));
    assertThat(subject("2 - 3"), is(-1L));
    assertThat(subject("3 - 2"), is(1L));
  }

  @Test
  void two() {
    assertThat(subject("10 - 7"), is(3L));
  }

  @Test
  void three() {
    assertThat(subject("2 + 3 + 6"), is(11L));
    assertThat(subject("2 - 3 - 6"), is(-7L));
  }

  @Test
  void four() {
    assertThat(subject("2 - 3 + 6 - 5"), is(0L));
    assertThat(subject("2 - 3 + 6 - 1"), is(4L));
    assertThat(subject("99+112-1823+34+3"), is(-1575L));
  }

  @Test
  void multiply() {
    assertThat(subject("2 * 6"), is(12L));
    assertThat(subject("2 * 6 - 2"), is(10L));
    assertThat(subject("2 - 6 * 2"), is(-10L));
    assertThat(subject("2 * 3 * 4 - 6 + 7 * 8"), is(74L));
  }

  @Test
  void overflow() {
    assertThat(subject("9223372036854775806 + 1"), is(9223372036854775807L));
  }

  @Test
  void divide() {
    assertThat(subject("6 / 3"), is(2L));
  }

  @Test
  void divideByZero() {
    assertThrows(ArithmeticException.class, () -> subject("1 / 0"));
    assertThrows(ArithmeticException.class, () -> subject("5*6+6/0-10"));
  }

  private long subject(String expression) {
    return new Calculator().calculate(expression);
  }
}