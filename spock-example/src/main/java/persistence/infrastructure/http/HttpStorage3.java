package persistence.infrastructure.http;

import static java.lang.String.format;
import static persistence.infrastructure.http.IsRemoved2.isDeleted;
import static persistence.infrastructure.http.IsRemoved2.statusCodeIs200;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscribers;
import java.util.concurrent.CompletableFuture;
import operation.domain.CannotRemoveException;
import persistence.domain.Storage;

public class HttpStorage3 implements Storage {
  private final String httpStorageEndpoint;
  private final IsRemoved2 isRemoved2;
  private final ResponseHandler responseHandler;

  public HttpStorage3(String httpStorageEndpoint) {
    this.httpStorageEndpoint = httpStorageEndpoint;
    this.isRemoved2 = response -> statusCodeIs200().and(isDeleted()).test(response);
    this.responseHandler = response -> {
      if (isRemoved2.test(response)) {
        return;
      }
      throw new CannotRemoveException("status code != 200");
    };
  }

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
      .thenAccept(response -> responseHandler.handle(toRemoveResponse(response)));
  }

  private RemoveResponse toRemoveResponse(HttpResponse<Void> response) {
    return RemoveResponse.builder()
      .statusCode(response.statusCode())
      .xLineStorageDeleted(response.headers().firstValue("x-line-storage-deleted"))
      .build();
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
