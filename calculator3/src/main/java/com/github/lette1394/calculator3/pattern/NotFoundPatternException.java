package com.github.lette1394.calculator3.pattern;

public class NotFoundPatternException extends RuntimeException {
  public NotFoundPatternException() {
    super();
  }

  public NotFoundPatternException(String message) {
    super(message);
  }

  public NotFoundPatternException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundPatternException(Throwable cause) {
    super(cause);
  }
}
