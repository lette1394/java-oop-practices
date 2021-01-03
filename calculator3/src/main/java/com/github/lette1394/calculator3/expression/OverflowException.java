package com.github.lette1394.calculator3.expression;

public class OverflowException extends RuntimeException {
  public OverflowException() {
    super();
  }

  public OverflowException(String message) {
    super(message);
  }

  public OverflowException(String message, Throwable cause) {
    super(message, cause);
  }

  public OverflowException(Throwable cause) {
    super(cause);
  }
}
