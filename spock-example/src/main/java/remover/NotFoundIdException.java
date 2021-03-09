package remover;

class NotFoundIdException extends RuntimeException {
  public NotFoundIdException(String message) {
    super(message);
  }
}