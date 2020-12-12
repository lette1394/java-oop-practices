package com.github.lette1394.numberbaseball;

public class Game {

  public Result run(Digits com, Digits user) {
    return Result.builder()
      .strike(com.strikes(user))
      .ball(com.balls(user))
      .build();
  }
}
