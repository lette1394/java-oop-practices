package com.github.lette1394.numberbaseball;

import lombok.Value;

@Value
public class Digit {
  int index;
  int value;

  public Digit(int index, int value) {
    this.index = index;
    this.value = value;

    Contracts.requires(0 <= value && value <= 9, "value requires 0 <= value <= 9");
    // FIXME (jaeeun) 2020-12-13:
    //  hard coded index range
    Contracts.requires(0 <= index && index < 3, "index requires 0 <= index < 3");
  }

  public boolean matchIndex(Digit other) {
    return index == other.index;
  }

  public boolean matchValue(Digit other) {
    return value == other.value;
  }
}
