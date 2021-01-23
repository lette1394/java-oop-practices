package com.github.lette1394.solid.X_1_input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_7 {
  private String workingDirectory;

  public FileStore_7(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException();
    }

    // LIKE DOCUMENTATION ! for programmer
    // - 어떠한 상황인가?
    // - 문제가 무엇인가?
    // - 원인이 무엇인가?
    // - 어떻게 해결하는가?
    // - 유머?
    if (!Files.exists(Path.of(workingDirectory))) {
      throw new IllegalArgumentException(String.format(
        "You tried to provide a working directory which does not exist."
          + "The FileStore class prevented to work in illegal state, "
          + "so you have to check that a valid working directory exists in your filesystem. "
          + "This exception was created from statically typed pre-condition."
          + "workingDirectory: [%s]", workingDirectory));
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

// - NO context switching between code and documentation system
// - closer to the code (the problem lives)
// - not aimed to user, but programmer