package com.github.lette1394.solid.X_3_SOLID_SRP;

import java.io.IOException;
import java.nio.file.Path;

public class Files {
  public static String readAllBytes(Path path) {
    try {
      return new String(java.nio.file.Files.readAllBytes(path));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public static Path write(Path path, String string) {
    try {
      return java.nio.file.Files.write(path, string.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
