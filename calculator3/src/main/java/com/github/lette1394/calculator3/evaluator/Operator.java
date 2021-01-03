package com.github.lette1394.calculator3.evaluator;

abstract class Operator {
  protected static String toInteger(String value) {
    if (value.contains(".")) {
      final int dot = value.indexOf(".");
      return value.substring(0, dot);
    }
    return value;
  }

  protected static String toString(double value) {
    final String result = String.valueOf(value);
    if (isInteger(result)) {
      return toInteger(result);
    }
    return result;
  }

  protected static String toString(long value) {
    return String.valueOf(value);
  }

  protected static double parseDouble(String value) {
    return Double.parseDouble(value);
  }

  protected static long parseLong(String value) {
    return Long.parseLong(value);
  }

  protected static boolean isInteger(String value) {
    return !isDecimal(value);
  }

  protected static boolean isDecimal(String value) {
    return value.contains(".") && !haveAllZero(value);
  }

  private static boolean haveAllZero(String expression) {
    final String substring = expression.substring(expression.indexOf(".") + 1); // next index of dot
    final String[] characters = substring.split("");
    for (String ch : characters) {
      if (ch.equals("0")) {
        continue;
      }
      return false;
    }
    return true;
  }
}
