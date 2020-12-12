package com.github.lette1394.numberbaseball;

public class Game {

  public Result run(DigitStream com, DigitStream user) {
    int strike = com.strikes(user);
    int ball = com.balls(user);

    return Result.builder()
      .ball(ball)
      .strike(strike)
      .build();
  }
}
