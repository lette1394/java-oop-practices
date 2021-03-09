package persistence.domain;

public class NotFoundIdException extends RuntimeException {
  public NotFoundIdException(String message) {
    super(message);
  }
}