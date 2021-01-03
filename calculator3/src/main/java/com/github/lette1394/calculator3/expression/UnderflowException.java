package com.github.lette1394.calculator3.expression;

public class UnderflowException extends RuntimeException {
  public UnderflowException() {
    super();
  }

  public UnderflowException(String message) {
    super(message);
  }

  public UnderflowException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnderflowException(Throwable cause) {
    super(cause);
  }
}
