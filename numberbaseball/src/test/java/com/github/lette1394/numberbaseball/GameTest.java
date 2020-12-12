package com.github.lette1394.numberbaseball;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class GameTest {

  @Test
  void test1() {
    assertThat(new Game().run(new int[]{1, 2, 3}, new int[]{1, 2, 3}), is(
      Result.builder()
        .ball(0)
        .strike(3)
        .build()));
  }

  @Test
  void test2() {
    assertThat(new Game().run(new int[]{1, 2, 3}, new int[]{1, 2, 4}), is(
      Result.builder()
        .ball(0)
        .strike(2)
        .build()));
  }

  @Test
  void test3() {
    assertThat(new Game().run(new int[]{1, 2, 3}, new int[]{1, 4, 2}), is(
      Result.builder()
        .ball(1)
        .strike(1)
        .build()));
  }
}