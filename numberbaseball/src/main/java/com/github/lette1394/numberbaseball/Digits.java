package com.github.lette1394.numberbaseball;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Digits {
  private final int[] value;

  public int strikes(Digits other) {
    int ret = 0;

    for (int i = 0; i < other.value.length; i++) {
      if (value[i] == other.value[i]) {
        ret++;
      }
    }
    return ret;
  }

  public int balls(Digits other) {
    int ret = 0;

    for (int i = 0; i < other.value.length; i++) {
      for (int j = 0; j < other.value.length; j++) {
        if (value[i] == other.value[j] && i != j) {
          ret++;
        }
      }
    }
    return ret;
  }
}
