package com.github.lette1394.calculator3.expression;

public class AdderInNumeric extends Operator implements Adder {
  @Override
  public String add(String left, String right) {
    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) + parseDouble(right));
    }
    return toString(parseLong(left) + parseLong(right));
  }
}
