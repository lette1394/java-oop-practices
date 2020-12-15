package com.github.lette1394.calculator2.operator;

public class OperatorNotFoundException extends RuntimeException {
  public OperatorNotFoundException() {
    super();
  }

  public OperatorNotFoundException(String message) {
    super(message);
  }

  public OperatorNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public OperatorNotFoundException(Throwable cause) {
    super(cause);
  }
}
