package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

public class NumericAdderExactly extends Operator implements Adder {
  @Override
  public String add(String left, String right) {
    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) + parseDouble(right));
    }

    return addIntegerExactly(left, right);
  }

  private void checkOverflow(double left, double right) {
    final double result = left + right;
    if (result == Double.POSITIVE_INFINITY) {
      throw new OverflowException(format("%s + %s", left, right));
    }
  }

  private String addIntegerExactly(String left, String right) {
    try {
      return toString(Math.addExact(parseLong(left), parseLong(right)));
    } catch (ArithmeticException e) {
      throw new OverflowException(format("%s + %s", left, right), e);
    }
  }
}
