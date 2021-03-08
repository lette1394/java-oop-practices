package com.github.lette1394.solid.X_7_SOLID_DIP_5;

import com.github.lette1394.solid.X_7_SOLID_DIP_4.FileStore;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.InMemoryStoreCache;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.MessageStore;
import com.github.lette1394.solid.X_7_SOLID_DIP_4.Slf4jStoreLogger;

public class Main {
  public static void main(String[] args) {
    var currentDirectory = "<directory>";
    var fileStore = new FileStore(currentDirectory);
    var cached = new InMemoryStoreCache(fileStore, fileStore);
    var logged = new Slf4jStoreLogger(cached, cached);

    var messageStore = new MessageStore(
      logged,
      logged,
      fileStore);
  }
}