package com.github.lette1394.solid.X_7_SOLID_DIP_3;

import java.io.File;
import java.util.Optional;

public class MessageStore {
  private final InMemoryStoreCache cache;
  private final Slf4jStoreLogger log;
  private final FileStore store;
  private final FileLocator fileLocator;

  private final StoreWriter writer;
  private final StoreReader reader;

  public MessageStore(String workingDirectory) {
    this.store = new FileStore(workingDirectory);
    this.fileLocator = store;
    this.cache = new InMemoryStoreCache(store, store);
    this.log = new Slf4jStoreLogger(cache, cache);
    this.writer = log;
    this.reader = log;
  }

  public void save(long id, String message) {
    writer().save(id, message);
  }

  public Optional<String> read(long id) {
    return reader().read(id);
  }







  public File getFile(long id) {
    return fileLocator().getFile(id);
  }

  // factory method
  public StoreWriter cache() {
    return cache;
  }

  public Slf4jStoreLogger log() {
    return log;
  }

  public StoreWriter store() {
    return store;
  }

  public FileLocator fileLocator() {
    return fileLocator;
  }

  public StoreWriter writer() {
    return writer;
  }

  public StoreReader reader() {
    return reader;
  }
}






























