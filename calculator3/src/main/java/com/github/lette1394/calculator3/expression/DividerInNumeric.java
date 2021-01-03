package com.github.lette1394.calculator3.expression;

import com.github.lette1394.calculator3.common.Contracts;

public class DividerInNumeric extends Operator implements Divider {
  @Override
  public String divide(String left, String right) {
    Contracts.requires(parseDouble(right) != 0, "cannot divide by zero");

    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) / parseDouble(right));
    }
    return toString(parseLong(left) / parseLong(right));
  }
}
