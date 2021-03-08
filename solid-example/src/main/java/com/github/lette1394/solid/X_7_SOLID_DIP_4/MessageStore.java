package com.github.lette1394.solid.X_7_SOLID_DIP_4;

import java.io.File;
import java.util.Optional;

public class MessageStore {
  private final StoreReader reader;
  private final StoreWriter writer;
  private final FileLocator fileLocator;

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





























