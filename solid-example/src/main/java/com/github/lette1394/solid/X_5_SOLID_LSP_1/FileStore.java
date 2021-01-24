package com.github.lette1394.solid.X_5_SOLID_LSP_1;

import com.github.lette1394.solid.X_3_SOLID_SRP.Files;
import java.io.File;
import java.nio.file.Path;

public class FileStore implements Store {
  public String readAllText(Path path) {
    return Files.readAllBytes(path);
  }

  public void writeAllText(Path path, String string) {
    Files.write(path, string);
  }

  public File getFile(long id, String workingDirectory) {
    return Path.of(workingDirectory, id + ".txt").toFile();
  }
}
