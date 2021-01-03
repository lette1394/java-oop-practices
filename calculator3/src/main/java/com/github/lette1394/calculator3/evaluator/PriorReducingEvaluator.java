package com.github.lette1394.calculator3.evaluator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

// FIXME (jaeeun) 2020-12-16: 의존성 제거 // CalculatorBuilder 등으로
@RequiredArgsConstructor
public class PriorReducingEvaluator implements Evaluator {
  private final Pattern pattern;
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
      final String mid = matcher.group(2);
      final String right = matcher.group(3);

      return evaluator.evaluate(evaluate(left) + mid + evaluate(right));
    }
    return evaluator.evaluate(expression);
  }

  @Override
  public String toString() {
    return "";
  }
}
