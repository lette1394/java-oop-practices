package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

import com.github.lette1394.calculator3.pattern.NotFoundPatternException;
import com.github.lette1394.calculator3.pattern.PatternFinder;

public abstract class BaseEvaluator implements Evaluator {
  private static final int INDEX_NOT_FOUND = -1;

  private final PatternFinder patternFinder;

  protected BaseEvaluator(PatternFinder patternFinder) {
    this.patternFinder = patternFinder;
  }

  protected final String find(String expression) {
    try {
      return patternFinder.find(expression);
    } catch (NotFoundPatternException e) {
      throw new UnsupportedExpressionException(format("Not supported expression: %s", expression), e);
    }
  }

  protected final String substringAfter(String string, String separator) {
    if (string == null) {
      throw new UnsupportedExpressionException();
    }
    if (string.isEmpty()) {
      throw new UnsupportedExpressionException();
    }
    if (separator == null) {
      throw new UnsupportedExpressionException();
    }

    final int pos = string.indexOf(separator);
    if (pos == INDEX_NOT_FOUND) {
      throw new UnsupportedExpressionException();
    }

    try {
      return string.substring(pos + separator.length());
    } catch (IndexOutOfBoundsException cause) {
      throw new UnsupportedExpressionException(cause);
    }
  }
}
