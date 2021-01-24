package com.github.lette1394.solid.X_6_SOLID_ISP_2;

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

  // 모든 곳에서 ISP를 적용할 수 있을까?
  public void save(long id, String message) {

    // 아래 4개의 메서드의 공통점은 무엇일까?
    log().saving(id);
    store().writeAllText(id, message);
    cache().put(id, message);
    log().saved(id);


    // 일단 이름은 같지 않다
    // 1. id를 parameter로 받는다
    // 2. 전부 void를 반환한다. => 모두 command이다.
    // 3. message parameter를 받기도 하고, 안 받기도 한다
    //
    // save() 메서드는 항상 messgae를 입력으로 받으므로
    // 이 메서드 안에서는 항상 messsage를 사용할 수 있다.

    // save() 메서드가 client 인데,
    // client가 interface를 결정하므로,
    // log().saving(id); 에서
    // log().saving(id, message); 으로 변경할 수 있다.

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






























