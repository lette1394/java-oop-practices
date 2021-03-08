package com.github.lette1394.solid.X_7_SOLID_DIP_5.message;

import com.github.lette1394.solid.X_7_SOLID_DIP_4.FileLocator;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.FileStore;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.InMemoryStoreCache;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.Slf4jStoreLogger;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.StoreReader;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.StoreWriter;
import java.io.File;
import java.util.Optional;

public class MessageStore {
  private final StoreReader reader;
  private final StoreWriter writer;
  private final FileLocator fileLocator;

  public MessageStore(String workingDirectory) {
    FileStore store = new FileStore(workingDirectory);
    InMemoryStoreCache cache = new InMemoryStoreCache(store, store);
    Slf4jStoreLogger logger = new Slf4jStoreLogger(cache, cache);

    this.fileLocator = store;
    this.writer = logger;
    this.reader = logger;
  }

  public MessageStore(StoreReader reader, StoreWriter writer, FileLocator fileLocator) {
    this.reader = reader;
    this.writer = writer;
    this.fileLocator = fileLocator;
  }

  public void save(long id, String message) {
    writer.save(id, message);
  }

  public Optional<String> read(long id) {
    return reader.read(id);
  }

  public File getFile(long id) {
    return fileLocator.getFile(id);
  }
}





























