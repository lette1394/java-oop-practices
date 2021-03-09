package persistence.domain;

import java.util.concurrent.CompletableFuture;

public interface Storage {
  CompletableFuture<Void> save(String id, String message);
  CompletableFuture<Void> remove(String id);
  CompletableFuture<Boolean> exists(String id);
}
