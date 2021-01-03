package com.github.lette1394.calculator3.evaluator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SequentialReducingEvaluator implements Evaluator {
  private static final Pattern pattern = Pattern.compile(
    "\\s*(-?\\d+\\.\\d+|-?\\d+)\\s*([^.]\\D+)\\s*(-?\\d+\\.\\d+|-?\\d+).*");
  private final Evaluator evaluator;
  private final JustEvaluator just = new JustEvaluator();

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    if (just.matches(expression)) {
      return expression;
    }

    final Matcher matcher = pattern.matcher(expression);
    if (matcher.matches()) {
      final String partial = matcher.group(1) + matcher.group(2) + matcher.group(3);
      final String rest = expression.substring(matcher.end(3));
      final String evaluated = evaluator.evaluate(partial);

      return evaluate(evaluated + rest);
    }
    return expression;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
