package com.github.lette1394.calculator3.evaluator;

public class MultiplierInNumeric extends Operator implements Multiplier {
  @Override
  public String multiply(String left, String right) {
    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) * parseDouble(right));
    }
    return toString(parseLong(left) * parseLong(right));
  }
}
