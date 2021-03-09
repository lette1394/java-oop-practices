package remover;

import static java.lang.String.format;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscribers;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpStorage implements Storage {
  private final String httpStorageEndpoint;

  @Override
  public CompletableFuture<Void> save(String id, String message) {
    throw new UnsupportedOperationException();
  }

  @Override
  public CompletableFuture<Void> remove(String id) {
    return HttpClient.newHttpClient()
      .sendAsync(makeRequest(id), responseInfo -> BodySubscribers.discarding())
      .exceptionally(throwable -> {
        throw new CannotRemoveException(throwable);
      })
      .thenAccept(response -> {
        if (statusCodeIs200().and(isDeleted()).test(response)) {
          return;
        }
        throw new CannotRemoveException("status code != 200");
      });
  }

  @Override
  public CompletableFuture<Boolean> exists(String id) {
    throw new UnsupportedOperationException();
  }

  private HttpRequest makeRequest(String id) {
    return HttpRequest.newBuilder()
      .DELETE()
      .uri(URI.create(format("%s/id/%s", httpStorageEndpoint, id)))
      .build();
  }

  private static Predicate<HttpResponse<Void>> statusCodeIs200() {
    return response -> response.statusCode() == 200;
  }

  private static Predicate<HttpResponse<Void>> isDeleted() {
    return response -> response.headers()
      .firstValue("x-line-storage-deleted")
      .map(deleted -> deleted.equals("true")).orElse(false);
  }
}
