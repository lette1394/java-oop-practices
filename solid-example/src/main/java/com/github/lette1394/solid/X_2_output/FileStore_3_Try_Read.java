package com.github.lette1394.solid.X_2_output;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_3_Try_Read {
  private String workingDirectory;

  public static void main(String[] args) {
    final FileStore_3_Try_Read fileStore = new FileStore_3_Try_Read();

    // - not OOP-like
    // - break fluent API
    // - easy to ignore boolean return value
    // - but, chance to be thread-safe
    List<String> result = new ArrayList<>();
    boolean exists = fileStore.tryRead(13, result);
    if (exists) {
      String message = result.get(0);
    }
  }

  public boolean tryRead(long id, List<String> result) {
    final Path path = getFilePath(id);

    try {
      final byte[] bytes = Files.readAllBytes(path);
      result.add(new String(bytes));
      return true;
    } catch (Exception e) {
      // not IOException. catch all exceptions in order to return false
      return false;
    }
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }
}
