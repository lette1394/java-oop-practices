package com.github.lette1394.solid.X_7_SOLID_DIP_1;

import java.util.Optional;

public class DatabaseStore implements Store {
  public Optional<String> readAllText(long id) {
    // read from database;
    return Optional.of("");
  }

  public void save(long id, String string) {
    // write to database;
  }
}