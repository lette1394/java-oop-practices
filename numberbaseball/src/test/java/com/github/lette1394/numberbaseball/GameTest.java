package com.github.lette1394.numberbaseball;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class GameTest {

  @Test
  void success() {
    assertThat(new Game().run(new int[]{1, 2, 3}, new int[]{1, 2, 3}), is(
      Result.builder()
        .ball(0)
        .strike(3)
        .build()));
  }

  @Test
  void fail() {
    assertThat(new Game().run(new int[]{1, 2, 3}, new int[]{1, 2, 4}), is(
      Result.builder()
        .ball(0)
        .strike(2)
        .build()));
  }
}