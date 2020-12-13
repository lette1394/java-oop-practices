package com.github.lette1394.calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void two() {
    assertThat(subject("2 + 3"), is(5L));
    assertThat(subject("10 - 7"), is(3L));
  }

  @Test
  void three() {
    assertThat(subject("2 + 3 + 6"), is(11L));
  }

  @Test
  void four() {
    assertThat(subject("2 - 3 + 6 - 5"), is(0L));
  }

  private long subject(String expression) {
    return new Calculator().calculate(expression);
  }
}