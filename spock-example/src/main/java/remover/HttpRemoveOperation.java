package remover;

import java.util.concurrent.CompletionStage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpRemoveOperation implements AsyncRemoveOperation {
  private final Storage storage;

  @Override
  public CompletionStage<Void> remove(String id) {
    return storage.remove(id)
      .exceptionally(throwable -> {
        throw new CannotRemoveException(throwable);
      })
      .thenAccept(unused -> {});
  }

  //  @Override
//  public CompletableFuture<Void> remove(String id) {
//    return HttpClient.newHttpClient()
//      .sendAsync(HttpRequest.newBuilder()
//        .DELETE()
//        .uri(URI.create("http://google.com"))
//        .build(), responseInfo -> BodySubscribers.discarding())
//      .exceptionally(throwable -> {
//        throw new CannotRemoveException(throwable);
//      })
//      .thenAccept(response -> {
//        if (response.statusCode() == 200) {
//          return;
//        }
//        System.out.println(response);
//        throw new CannotRemoveException("status code != 200");
//      });
//  }
}
