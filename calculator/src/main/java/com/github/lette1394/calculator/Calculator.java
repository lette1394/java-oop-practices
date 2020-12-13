package com.github.lette1394.calculator;

import java.util.Arrays;

public class Calculator {
  public long calculate(String expression) {
    final String[] split = expression.split(" ");

    final long a = Long.parseLong(split[0]);
    final long b = Long.parseLong(split[2]);
    final String operator = split[1];

    if (operator.equals("+")) {
      return a + b;
    }

    if (operator.equals("-")) {
      return a - b;
    }

    return -1;
  }
}
