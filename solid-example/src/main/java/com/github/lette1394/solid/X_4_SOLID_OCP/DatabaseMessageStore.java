package com.github.lette1394.solid.X_4_SOLID_OCP;


public class DatabaseMessageStore extends MessageStore {
  private final FileStore fileStore;

  public DatabaseMessageStore(String workingDirectory) {
    super(workingDirectory);
    fileStore = new DatabaseStore();
  }

  @Override
  public FileStore fileStore() {
    return fileStore;
  }
}














class DatabaseStore extends FileStore {

}




























