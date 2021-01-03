package com.github.lette1394.calculator3.evaluator;

public class DivideByZeroException extends UnrecoverableException {
  public DivideByZeroException() {
    super();
  }

  public DivideByZeroException(String message) {
    super(message);
  }

  public DivideByZeroException(String message, Throwable cause) {
    super(message, cause);
  }

  public DivideByZeroException(Throwable cause) {
    super(cause);
  }
}
