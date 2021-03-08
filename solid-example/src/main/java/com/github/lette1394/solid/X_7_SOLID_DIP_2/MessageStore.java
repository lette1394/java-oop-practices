package com.github.lette1394.solid.X_7_SOLID_DIP_2;

import java.io.File;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("DuplicatedCode")
public class MessageStore {
  private final StoreCache cache;
  private final StoreLogger log;
  private final Store store;
  private final FileLocator fileLocator;
  private final StoreWriter writer;

  public MessageStore(String workingDirectory) {
    this.cache = new InMemoryStoreCache();
    this.log = new StoreLogger();
    this.store = new FileStore(workingDirectory);
    this.fileLocator = new FileStore(workingDirectory);

    this.writer = new CompositeStoreWriter(List.of(
      new LogSavingStoreWriter(),
      new FileStore(workingDirectory),
      new InMemoryStoreCache(),
      new LogSavedStoreWriter()
    ));
  }

  public void save(long id, String message) {
    writer().save(id, message);
  }






  // read() 하고 나서 값이 없으면 어떻게 하지?
  //  있으면 어떻게하지?
  //  Optional type 이므로 별도 supplier가 필요없다

  // read는 query라서 decorator 적용이 더 쉬움




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

  public StoreWriter writer() {
    return writer;
  }
}






























