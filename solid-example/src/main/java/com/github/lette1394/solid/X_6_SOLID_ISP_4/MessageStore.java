package com.github.lette1394.solid.X_6_SOLID_ISP_4;

import java.io.File;
import java.util.Optional;

public class MessageStore {
  private final IStoreCache cache;
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
    log().saving(id, message);
    store().save(id, message);
    cache().save(id, message);
     log().saved(id, message);

    // saving() 이랑 saved() 는 save()로 바꿀 수 없다.
    //
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
    return fileLocator().getFile(id);
  }

  // factory method
  public IStoreCache cache() {
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






























