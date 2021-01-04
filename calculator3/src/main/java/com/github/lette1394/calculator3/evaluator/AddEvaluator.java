package com.github.lette1394.calculator3.evaluator;

import static com.github.lette1394.calculator3.common.Contracts.requires;
import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddEvaluator implements Evaluator {
  private final static Pattern pattern =
    Pattern.compile("\\s*(-?\\d+\\.?\\d*)\\s*(\\+)\\s*(-?\\d+\\.?\\d*)\\s*");

  private final Adder adder;

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    final Matcher matcher = pattern.matcher(expression);
    requires(matcher.matches(),
      new UnsupportedExpressionException(format("Not supported expression: %s", expression)));

    final String left = matcher.group(1);
    final String right = matcher.group(3);
    return adder.add(left, right);
  }

  @Override
  public String toString() {
    return "add evaluator";
  }
}
