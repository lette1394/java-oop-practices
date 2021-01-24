package com.github.lette1394.solid.X_6_SOLID_ISP_3;

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

  // 모든 곳에서 ISP를 적용할 수 있을까?
  public void save(long id, String message) {

    // 아래 4개의 메서드의 공통점은 무엇일까?
    log().saving(id, message);
    store().writeAllText(id, message);
    cache().put(id, message);
    log().saved(id, message);

    // 이제는 이름만 다르다.
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






























