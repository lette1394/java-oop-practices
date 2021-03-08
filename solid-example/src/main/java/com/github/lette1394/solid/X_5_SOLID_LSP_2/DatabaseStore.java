package com.github.lette1394.solid.X_5_SOLID_LSP_2;

import java.io.File;
import java.util.Optional;

public class DatabaseStore implements Store {
  public Optional<String> readAllText(long id) {
    // read from database;
    return Optional.of("");
  }

  public void writeAllText(long id, String string) {
    // write to database;
  }

  public File getFile(long id) {
    // LSP 위반!
    throw new UnsupportedOperationException();
  }
}