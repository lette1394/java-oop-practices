package com.github.lette1394.solid.X_1_input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_5 {
  private String workingDirectory;

  public FileStore_5(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException();
    }
    this.workingDirectory = workingDirectory;
  }

  public void save(long id, String message) throws IOException {
    final Path path = getFilePath(id);
    Files.write(path, message.getBytes());
  }

  public String read(long id) throws IOException {
    final Path path = getFilePath(id);
    final byte[] bytes = Files.readAllBytes(path);
    return new String(bytes);
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }
}
