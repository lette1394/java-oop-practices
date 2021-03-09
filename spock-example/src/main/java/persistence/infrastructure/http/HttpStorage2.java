package persistence.infrastructure.http;

import static java.lang.String.format;
import static persistence.infrastructure.http.IsRemoved.isDeleted;
import static persistence.infrastructure.http.IsRemoved.statusCodeIs200;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodySubscribers;
import java.util.concurrent.CompletableFuture;
import operation.domain.CannotRemoveException;
import persistence.domain.Storage;

public class HttpStorage2 implements Storage {
  private final String httpStorageEndpoint;
  private final IsRemoved isRemoved;

  public HttpStorage2(String httpStorageEndpoint) {
    this.httpStorageEndpoint = httpStorageEndpoint;
    this.isRemoved = response -> statusCodeIs200().and(isDeleted()).test(response);
  }

  @Override
  public CompletableFuture<Void> remove(String id) {
    return HttpClient.newHttpClient()
      .sendAsync(makeRequest(id), responseInfo -> BodySubscribers.discarding())
      .exceptionally(throwable -> {
        throw new CannotRemoveException(throwable);
      })
      .thenAccept(response -> {
        if (isRemoved.test(response)) {
          return;
        }
        throw new CannotRemoveException("status code != 200");
      });
  }

  @Override
  public CompletableFuture<Void> save(String id, String message) {
    throw new UnsupportedOperationException();
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
}
