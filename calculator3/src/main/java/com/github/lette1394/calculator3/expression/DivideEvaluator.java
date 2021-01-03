package com.github.lette1394.calculator3.expression;

import static com.github.lette1394.calculator3.common.Contracts.requires;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DivideEvaluator implements Evaluator {
  private final static Pattern pattern =
    Pattern.compile("\\s*(-?\\d+\\.?\\d*)\\s*(/)\\s*(-?\\d+\\.?\\d*)\\s*");

  private final Divider divider;

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    final Matcher matcher = pattern.matcher(expression);
    requires(matcher.matches(), new UnsupportedExpressionException("Not supported expression"));

    final String left = matcher.group(1);
    final String right = matcher.group(3);

    requires(notZero(right),
      new DivideByZeroException(String.format("divide by zero: %s / %s", left, right)));
    return divider.divide(left, right);
  }

  @Override
  public String toString() {
    return "";
  }

  private boolean notZero(String value) {
    return Double.parseDouble(value) != 0;
  }
}
