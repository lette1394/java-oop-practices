package com.github.lette1394.solid.X_0_introduce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStore3 {
  private String workingDirectory;

  // command return void
  public void save(long id, String message) throws IOException {
    final Path path = getFilePath(id);
    Files.write(path, message.getBytes());
  }

  // query return data without side-effect
  public String read(long id) throws IOException {
    final Path path = getFilePath(id);
    final byte[] bytes = Files.readAllBytes(path);
    return new String(bytes);
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }
}

