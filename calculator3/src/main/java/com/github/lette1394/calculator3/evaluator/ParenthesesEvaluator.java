package com.github.lette1394.calculator3.evaluator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParenthesesEvaluator implements Evaluator {
  private static final Pattern pattern = Pattern.compile("(.*)(\\([^()]+\\))(.*)");
  private final Evaluator evaluator;

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    final Matcher matcher = pattern.matcher(expression);
    if (matcher.matches()) {
      final String left = matcher.group(1);
      final String mid = evaluate(unwrap(matcher.group(2)));
      final String right = matcher.group(3);
      return evaluator.evaluate(evaluate(left + mid + right));
    }
    return evaluator.evaluate(expression);
  }

  private String unwrap(String value) {
    final int length = value.length();
    return value.substring(1, length - 1);
  }
}
