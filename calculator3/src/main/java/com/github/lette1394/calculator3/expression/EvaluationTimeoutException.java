package com.github.lette1394.calculator3.expression;

import lombok.Getter;

@Getter
public class EvaluationTimeoutException extends UnrecoverableException {
  private final String expression;

  public EvaluationTimeoutException(String expression) {
    super();
    this.expression = expression;
  }

  public EvaluationTimeoutException(String message, String expression) {
    super(message);
    this.expression = expression;
  }

  public EvaluationTimeoutException(String message, Throwable cause, String expression) {
    super(message, cause);
    this.expression = expression;
  }

  public EvaluationTimeoutException(Throwable cause, String expression) {
    super(cause);
    this.expression = expression;
  }

  @Override
  public String toString() {
    return String.format("timeout occurred in [%s]", expression);
  }
}
