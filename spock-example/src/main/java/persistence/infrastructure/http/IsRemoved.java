package persistence.infrastructure.http;

import java.net.http.HttpResponse;
import java.util.function.Predicate;

@FunctionalInterface
public interface IsRemoved {
  boolean test(HttpResponse<Void> response);

  static Predicate<HttpResponse<Void>> statusCodeIs200() {
    return response -> response.statusCode() == 200;
  }

  static Predicate<HttpResponse<Void>> isDeleted() {
    return response -> response.headers()
      .firstValue("x-line-storage-deleted")
      .map(deleted -> deleted.equals("true")).orElse(false);
  }
}
