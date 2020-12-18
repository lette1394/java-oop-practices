package com.github.lette1394.calculator2.expression;

import java.util.regex.Pattern;

public class LongAddExpression extends LongExpression {
  private final static Pattern pattern = Pattern
    .compile("\\s*(\\d+\\.?\\d*)\\s*(\\+)\\s*(\\d+\\.?\\d*)\\s*");
  private final String expression;

  public LongAddExpression(String expression) throws UnsupportedOperationException {
    super(pattern.matcher(expression));
    this.expression = expression;
  }

  @Override
  protected Number whenUseLong(long left, long right) {
    return left + right;
  }

  @Override
  protected Number whenUseDouble(double left, double right) {
    return left + right;
  }

  @Override
  public String toString() {
    return expression;
  }
}
