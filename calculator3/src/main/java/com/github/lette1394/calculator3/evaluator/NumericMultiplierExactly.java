package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

public class NumericMultiplierExactly extends Operator implements Multiplier {
  @Override
  public String multiply(String left, String right) {
    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) * parseDouble(right));
    }
    return multiplyIntegerExactly(left, right);
  }

  private String multiplyIntegerExactly(String left, String right) {
    try {
      return toString(Math.multiplyExact(parseLong(left), parseLong(right)));
    } catch (ArithmeticException e) {
      throw new OverflowException(format("%s * %s", left, right), e);
    }
  }
}
