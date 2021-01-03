package com.github.lette1394.calculator3.expression;

public class UnrecoverableException extends RuntimeException {
  public UnrecoverableException() {
    super();
  }

  public UnrecoverableException(String message) {
    super(message);
  }

  public UnrecoverableException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnrecoverableException(Throwable cause) {
    super(cause);
  }
}
