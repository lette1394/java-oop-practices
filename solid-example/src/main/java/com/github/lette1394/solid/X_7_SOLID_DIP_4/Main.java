package com.github.lette1394.solid.X_7_SOLID_DIP_4;

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