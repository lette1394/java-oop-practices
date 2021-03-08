package com.github.lette1394.solid.X_6_SOLID_ISP_5;

import java.io.File;
import java.util.Optional;

public class MessageStore {
  private final StoreCache cache;
  private final StoreLogger log;
  private final Store store;
  private final FileLocator fileLocator;

  public MessageStore(String workingDirectory) {
    this.cache = new MemoryStoreCache();
    this.log = new StoreLogger();
    this.store = new FileStore(workingDirectory);
    this.fileLocator = new FileStore(workingDirectory);
  }





  public void save(long id, String message) {
    new LogSavingStoreWriter().save(id, message); // OCP 위반!!
                       store().save(id, message);
                       cache().save(id, message);
     new LogSavedStoreWriter().save(id, message); // OCP 위반!!

    // virtual property 를 사용할 수도 있지만 근본적 해결은 아님.
    // DIP 에서 더 다뤄보겠다.
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






























