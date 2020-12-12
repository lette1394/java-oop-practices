package com.github.lette1394.numberbaseball;

public class Digit {
  private final int digit;
  private final int index;

  public Digit(int digit, int index) {
    this.digit = digit;
    this.index = index;

    Contracts.requires(0 <= digit && digit <= 9, "digit requires 0 <= value <= 9");
    // FIXME (jaeeun) 2020-12-13:
    //  hard coded index range
    Contracts.requires(0 <= index && index < 3, "index requires 0 <= value < 3");
  }

  boolean matchValue(Digit other) {
    return digit == other.digit;
  }

  boolean matchIndex(Digit other) {
    return index == other.index;
  }
}
