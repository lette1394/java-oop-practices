package com.github.lette1394.solid.X_1_input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStore_3 {

  // INVARIANTS LIVES HERE !!
  // pre-condition: if it breaks, nothing is going to work.
  // "workingDirectory never be null"
  private String workingDirectory;

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
  }}
