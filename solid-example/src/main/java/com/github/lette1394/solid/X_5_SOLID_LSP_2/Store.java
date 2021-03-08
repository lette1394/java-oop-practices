package com.github.lette1394.solid.X_5_SOLID_LSP_2;

import java.io.File;
import java.util.Optional;

public interface Store {
  Optional<String> readAllText(long id);

  void writeAllText(long id, String string);

  File getFile(long id);
}







