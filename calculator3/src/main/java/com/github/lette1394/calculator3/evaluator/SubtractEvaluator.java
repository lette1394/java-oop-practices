package com.github.lette1394.calculator3.evaluator;

import static com.github.lette1394.calculator3.common.Contracts.requires;
import static java.lang.String.format;

import com.github.lette1394.calculator3.pattern.PatternMatcher;
import com.github.lette1394.calculator3.pattern.PatternMatcherResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubtractEvaluator implements Evaluator {
  private final static Pattern pattern =
    Pattern.compile("\\s*(-?\\d+\\.?\\d*)\\s*(-)\\s*(-?\\d+\\.?\\d*)\\s*");

  private final PatternMatcher patternMatcher;
  private final Subtractor subtractor;

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {

    final PatternMatcherResult match = patternMatcher.match(expression);
    return match.next().map(addExpression -> {
      if (addExpression.startsWith("-")) {
        final String[] split = addExpression.split("\\-");
        return subtractor.subtract("-"+split[1], split[2]);
      }
      final String[] split = addExpression.split("\\-");
      return subtractor.subtract(split[0], split[1]);
    })
      .orElseThrow(() -> new UnsupportedExpressionException(
        format("Not supported expression: %s", expression)));
  }

  @Override
  public String toString() {
    return "";
  }
}
