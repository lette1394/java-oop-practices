package com.github.lette1394.calculator3.evaluator;

public class SubtractorInNumeric extends Operator implements Subtractor {
  @Override
  public String subtract(String left, String right) {
    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) - parseDouble(right));
    }
    return toString(parseLong(left) - parseLong(right));
  }
}
