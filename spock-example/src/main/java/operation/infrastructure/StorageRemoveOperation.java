package operation.infrastructure;

import java.util.concurrent.CompletionStage;
import lombok.RequiredArgsConstructor;
import operation.domain.AsyncRemoveOperation;
import operation.domain.CannotRemoveException;
import persistence.domain.Storage;

@RequiredArgsConstructor
public class StorageRemoveOperation implements AsyncRemoveOperation {
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
