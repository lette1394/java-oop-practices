package com.github.lette1394.solid.X_6_SOLID_ISP_1;

import com.github.lette1394.solid.X_3_SOLID_SRP_1.StoreLogger;
import java.io.File;
import java.util.Optional;
import lombok.Getter;

public class MessageStore {
  @Getter
  private final StoreCache cache;
  private final StoreLogger log;
  private final Store store;
  private final FileLocator fileLocator;

  public MessageStore(String workingDirectory) {
    this.cache = new StoreCache();
    this.log = new StoreLogger();
    this.store = new FileStore(workingDirectory);
    this.fileLocator = new FileStore(workingDirectory);
  }

  public void save(long id, String message) {
    log().saving(id);
    store().writeAllText(id, message); // 이 메서드에서는 writeAllText()만 사용하고
    cache().put(id, message);
    log().saved(id);
  }

  public Optional<String> read(long id) {
    log().reading(id);
    Optional<String> message = cache()
      .computeIfAbsent(id, () -> store().readAllText(id)); // 이 메서드에서는 readAllText()만 사용하고

    if (message.isPresent()) {
      log().returning(id);
    } else {
      log().didNotFound(id);
    }
    return message;
  }

  public File getFile(long id) {
    return fileLocator().getFile(id); // 이 메서드에서는 getFile()만 사용하고 => ISP 위반!
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

  public FileLocator fileLocator() {
    return fileLocator;
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






























