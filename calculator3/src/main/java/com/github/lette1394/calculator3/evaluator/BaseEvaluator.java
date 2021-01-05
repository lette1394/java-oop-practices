package com.github.lette1394.calculator3.evaluator;

public abstract class BaseEvaluator implements Evaluator {
  private static final int INDEX_NOT_FOUND = -1;

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
