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
}
