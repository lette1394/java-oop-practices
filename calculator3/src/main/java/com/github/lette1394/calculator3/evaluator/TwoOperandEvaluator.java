package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

import com.github.lette1394.calculator3.pattern.PatternMatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class TwoOperandEvaluator implements Evaluator {
  private final PatternMatcher patternMatcher;
  private final Pattern form;

  public TwoOperandEvaluator(PatternMatcher patternMatcher, String operator) {
    this.patternMatcher = patternMatcher;
    this.form = Pattern.compile(format("(.+)%s(.+)", operator));
  }

  @Override
  public final String evaluate(String expression) throws UnsupportedExpressionException,
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
          return evaluate(left, right);
        }
        throw unsupported(expression);
      })
      .orElseThrow(() -> unsupported(expression));
  }

  protected abstract String evaluate(String left, String right) throws UnsupportedExpressionException,
    DivideByZeroException,
    OverflowException,
    UnderflowException,
    EvaluationTimeoutException;

  private RuntimeException unsupported(String expression) {
    return new UnsupportedExpressionException(
      format("Not supported expression: %s", expression));
  }
}
