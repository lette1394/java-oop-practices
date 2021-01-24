package com.github.lette1394.solid.X_5_SOLID_LSP;

import com.github.lette1394.solid.X_3_SOLID_SRP.Files;
import java.nio.file.Path;

public class FileStore {
  public String readAllBytes(Path path) {
    return Files.readAllBytes(path);
  }

  public void write(Path path, String string) {
    Files.write(path, string);
  }
}
