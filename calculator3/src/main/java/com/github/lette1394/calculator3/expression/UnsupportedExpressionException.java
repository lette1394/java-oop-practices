package com.github.lette1394.calculator3.expression;

public class UnsupportedExpressionException extends RuntimeException {
  public UnsupportedExpressionException() {
    super();
  }

  public UnsupportedExpressionException(String message) {
    super(message);
  }

  public UnsupportedExpressionException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnsupportedExpressionException(Throwable cause) {
    super(cause);
  }
}
