package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

public class NumericSubtractorExactly extends Operator implements Subtractor {
  @Override
  public String subtract(String left, String right) {
    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) - parseDouble(right));
    }
    return subtractIntegerExactly(left, right);
  }

  private String subtractIntegerExactly(String left, String right) {
    try {
      return toString(Math.subtractExact(parseLong(left), parseLong(right)));
    } catch (ArithmeticException e) {
      throw new OverflowException(format("%s - %s", left, right), e);
    }
  }
}
