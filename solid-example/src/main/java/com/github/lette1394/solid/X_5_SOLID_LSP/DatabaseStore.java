package com.github.lette1394.solid.X_5_SOLID_LSP;

import java.io.File;
import java.nio.file.Path;

public class DatabaseStore extends FileStore {
  public String readAllText(Path path) {
    // read from database;
    return "";
  }

  public void writeAllText(Path path, String string) {
    // write to database;
  }

  public File getFile(long id, String workingDirectory) {
    // LSP 위반!
    return super.getFile(id, workingDirectory);
  }
}


// 일단 계층이 이상하다
// SqlStore -> FileStore ?