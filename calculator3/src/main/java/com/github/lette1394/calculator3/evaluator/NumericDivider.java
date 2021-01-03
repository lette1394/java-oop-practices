package com.github.lette1394.calculator3.evaluator;

import com.github.lette1394.calculator3.common.Contracts;

public class NumericDivider extends Operator implements Divider {
  @Override
  public String divide(String left, String right) {
    Contracts.requires(parseDouble(right) != 0, "cannot divide by zero");

    if (isDecimal(left) || isDecimal(right)) {
      return toString(parseDouble(left) / parseDouble(right));
    }

    final long leftLong = parseLong(left);
    final long rightLong = parseLong(right);
    if (leftLong % rightLong == 0) {
      return toString(leftLong / rightLong);
    }
    return toString(((double) leftLong) / rightLong);
  }
}
