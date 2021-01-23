package com.github.lette1394.solid.X_2_output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_2_Tester_Doer {
  private String workingDirectory;

  public static void main(String[] args) {
    final FileStore_2_Tester_Doer fileStore = new FileStore_2_Tester_Doer();

    // main-problem: not thread-safe
    String message = "";
    if (fileStore.exists(13)) {
      message = fileStore.read(13);
    }
  }

  public boolean exists(long id) {
    return Files.exists(getFilePath(id));
  }

  public String read(long id) {
    final Path path = getFilePath(id);

    try {
      final byte[] bytes = Files.readAllBytes(path);
      return new String(bytes);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }
}
