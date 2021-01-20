package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

import com.github.lette1394.calculator3.pattern.PatternMatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubtractEvaluator implements Evaluator {
  private final static Pattern form = Pattern.compile("(.+)-(.+)");
  private final PatternMatcher patternMatcher;
  private final Subtractor subtractor;

  @Override
  public String evaluate(String expression) throws UnsupportedExpressionException,
                                                   DivideByZeroException,
                                                   OverflowException,
                                                   UnderflowException,
                                                   EvaluationTimeoutException {
    return patternMatcher
      .match(expression)
      .next()
      .map(partial -> {
        final Matcher matcher = form.matcher(partial);
        if (matcher.matches()) {
          final String left = matcher.group(1);
          final String right = matcher.group(2);
          return subtractor.subtract(left, right);
        }
        throw unsupported(expression);
      })
      .orElseThrow(() -> unsupported(expression));
  }

  private RuntimeException unsupported(String expression) {
    return new UnsupportedExpressionException(
      format("Not supported expression: %s", expression));
  }

  @Override
  public String toString() {
    return "";
  }
}
