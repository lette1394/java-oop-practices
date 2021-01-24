package com.github.lette1394.solid.X_7_SOLID_DIP_1;

import java.util.Optional;

public interface Store {
  Optional<String> readAllText(long id);

  void save(long id, String string);
}


