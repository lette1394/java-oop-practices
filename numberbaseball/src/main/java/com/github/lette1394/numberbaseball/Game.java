package com.github.lette1394.numberbaseball;

public class Game {

  public Result run(int[] rawCom, int[] rawUser) {
    final Digits com = Digits.of(rawCom);
    final Digits user = Digits.of(rawUser);

    System.out.println("strikes: " + com.strikes(user));
    System.out.println("balls: " + com.balls(user));

    return Result.builder()
      .strike(com.strikes(user).count())
      .ball(com.balls(user).count())
      .build();
  }
}
