package com.github.lette1394.calculator2;

public class Calculator {
  public static Result calculate(String expression) {
    return Expressions
      .parse(expression)
      .evaluate();
  }
}
