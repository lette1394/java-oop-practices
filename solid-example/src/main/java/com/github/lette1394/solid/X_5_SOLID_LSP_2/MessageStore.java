package com.github.lette1394.solid.X_5_SOLID_LSP_2;

import com.github.lette1394.solid.X_3_SOLID_SRP_1.StoreLogger;
import java.io.File;
import java.util.Optional;
import lombok.Getter;

public class MessageStore {
  @Getter
  private final StoreCache cache;
  private final StoreLogger log;
  private final Store store;

  public MessageStore(String workingDirectory) {
    this.cache = new StoreCache();
    this.log = new StoreLogger();
    this.store = new FileStore(workingDirectory);
  }

  public void save(long id, String message) {
    log().saving(id);
    store().writeAllText(id, message);
    cache().put(id, message);
    log().saved(id);
  }

  public Optional<String> read(long id) {
    log().reading(id);
    Optional<String> message = cache()
      .computeIfAbsent(id, () -> store().readAllText(id));

    if (message.isPresent()) {
      log().returning(id);
    } else {
      log().didNotFound(id);
    }
    return message;
  }

  public File getFile(long id) {
    return store().getFile(id);
  }

  // factory method
  public StoreCache cache() {
    return cache;
  }

  public StoreLogger log() {
    return log;
  }

  public Store store() {
    return store;
  }
}
//
//
//
//
//
//
//
//





























