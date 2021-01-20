package com.github.lette1394.solid.X_1_input;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class FileStore_3 {

  // INVARIANTS LIVES HERE !!
  // pre-condition: if it breaks, nothing is going to work.
  // "workingDirectory never be null"
  private String workingDirectory;

  @SneakyThrows
  public void save(long id, String message) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    Files.write(path, message.getBytes());
  }

  @SneakyThrows
  public String read(long id) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    final byte[] bytes = Files.readAllBytes(path);
    return new String(bytes);
  }

  public String getFilePath(long id) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    return path.toString();
  }
}