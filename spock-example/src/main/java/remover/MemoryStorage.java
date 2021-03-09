package remover;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.failedFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MemoryStorage implements Storage {
  private final Map<String, String> holder = new HashMap<>();

  public CompletableFuture<Void> save(String id, String message) {
    holder.put(id, message);
    return CompletableFuture.completedFuture(null);
  }

  public CompletableFuture<Void> remove(String id) {
    if (holder.containsKey(id)) {
      holder.remove(id);
      return completedFuture(null);
    }
    return failedFuture(new NotFoundIdException(format("id:[%s] not exist", id)));
  }

  public CompletableFuture<Boolean> exists(String id) {
    return completedFuture(holder.containsKey(id));
  }
}
