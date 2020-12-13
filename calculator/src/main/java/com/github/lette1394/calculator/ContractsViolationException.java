package com.github.lette1394.calculator;

public class ContractsViolationException extends RuntimeException {
  public ContractsViolationException() {
    super();
  }

  public ContractsViolationException(String message) {
    super(message);
  }

  public ContractsViolationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ContractsViolationException(Throwable cause) {
    super(cause);
  }
}
