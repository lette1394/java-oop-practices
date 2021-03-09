package persistence.infrastructure.http;

import java.util.function.Predicate;

@FunctionalInterface
public interface IsRemoved2 {
  boolean test(RemoveResponse response);

  static Predicate<RemoveResponse> statusCodeIs200() {
    return response -> response.getStatusCode() == 200;
  }

  static Predicate<RemoveResponse> isDeleted() {
    return response -> response.getXLineStorageDeleted()
      .map(deleted -> deleted.equals("true"))
      .orElse(false);
  }
}
