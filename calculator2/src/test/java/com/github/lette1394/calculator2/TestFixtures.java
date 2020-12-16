package com.github.lette1394.calculator2;

import com.github.lette1394.calculator2.result.Result;

public class TestFixtures {
  public static Result subject(String expression) {
    return Calculator.calculate(expression);
  }

  public static String subjectString(String expression) {
    return subject(expression).asString();
  }

  public static long subjectLong(String expression) {
    return subject(expression).asLong();
  }

  public static long subjectLongExact(String expression) {
    return subject(expression).asLongExact();
  }

  public static double subjectDouble(String expression) {
    return subject(expression).asDouble();
  }
}
