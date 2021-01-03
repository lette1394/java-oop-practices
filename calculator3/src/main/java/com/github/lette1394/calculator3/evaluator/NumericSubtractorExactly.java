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
      throw new UnderflowException(format("%s - %s", left, right), e);
    }
  }

  public static void main(String[] args) {
    System.out.println(Double.MAX_VALUE + 9999999999999999L);
    final double v = Double.MAX_VALUE + 9999999999999999L;
    System.out.println(v == Double.MAX_VALUE);
    System.out.println(1.7976931348623157e+308 == Double.MAX_VALUE);
    System.out.println(Double.parseDouble("1.7976931348623157e+308") == Double.MAX_VALUE);
    System.out.println(Double.isFinite(v));

    System.out.println(Double.isInfinite(v));
    System.out.println(Double.isNaN(v));

    System.out.println();
    System.out.println(Double.parseDouble("1.7e+309") == (1.0 / 0.0));
  }
}
