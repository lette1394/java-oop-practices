package com.github.lette1394.calculator3.evaluator;

import java.util.regex.Pattern;

public class JustEvaluator implements Evaluator {
  private final Pattern integerPattern = Pattern.compile("\\s*(-?\\d+)\\s*");
  private final Pattern decimalPattern = Pattern.compile("\\s*(-?\\d+\\.\\d+)\\s*");

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    if (matches(expression)) {
      return expression;
    }
    throw new UnsupportedExpressionException("Not supported expression");
  }

  public boolean matches(String expression) {
    return isInteger(expression) || isDecimal(expression);
  }

  private boolean isInteger(String expression) {
    return integerPattern.matcher(expression).matches();
  }

  private boolean isDecimal(String expression) {
    return decimalPattern.matcher(expression).matches();
  }
}
