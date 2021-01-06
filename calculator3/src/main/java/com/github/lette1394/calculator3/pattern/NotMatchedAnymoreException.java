package com.github.lette1394.calculator3.pattern;

public class NotMatchedAnymoreException extends RuntimeException {
  public NotMatchedAnymoreException() {
    super();
  }

  public NotMatchedAnymoreException(String message) {
    super(message);
  }

  public NotMatchedAnymoreException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotMatchedAnymoreException(Throwable cause) {
    super(cause);
  }
}
