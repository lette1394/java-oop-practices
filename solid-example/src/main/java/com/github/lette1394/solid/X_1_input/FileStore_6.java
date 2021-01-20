package com.github.lette1394.solid.X_1_input;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_6 {
  private String workingDirectory;

  public FileStore_6(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException();
    }
    if (!Files.exists(Path.of(workingDirectory))) {
      throw new IllegalArgumentException();
    }
    this.workingDirectory = workingDirectory;
  }

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
