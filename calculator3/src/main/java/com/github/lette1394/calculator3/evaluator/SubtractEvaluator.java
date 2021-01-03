package com.github.lette1394.calculator3.evaluator;

import static com.github.lette1394.calculator3.common.Contracts.requires;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubtractEvaluator implements Evaluator {
  private final static Pattern pattern =
    Pattern.compile("\\s*(-?\\d+\\.?\\d*)\\s*(-)\\s*(-?\\d+\\.?\\d*)\\s*");

  private final Subtractor subtractor;

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
    return subtractor.subtract(left, right);
  }

  @Override
  public String toString() {
    return "";
  }
}
