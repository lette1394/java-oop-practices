package com.github.lette1394.numberbaseball;

public class Game {

  public Result run(int[] rawCom, int[] rawUser) {
    final Digits com = Digits.of(rawCom);
    final Digits user = Digits.of(rawUser);

    return Result.builder()
      .strike(com.strikes(user).count())
      .ball(com.balls(user).count())
      .build();
  }
}
