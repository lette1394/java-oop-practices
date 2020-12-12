package com.github.lette1394.numberbaseball;

public class Game {

  public Result run(int[] com, int[] user) {
    int strike = 0;
    int ball = 0;

    for (int i = 0; i < com.length; i++) {
      for (int j = 0; j < user.length; j++) {
        if (com[i] == user[j] && i == j) { // 숫자와 위치가 일치
          strike++;
        } else if (com[i] == user[j] && i != j) { // 위치는 다르나 숫자는 일치
          ball++;
        }
      }
    }

    return Result.builder()
      .ball(ball)
      .strike(strike)
      .build();
  }
}
