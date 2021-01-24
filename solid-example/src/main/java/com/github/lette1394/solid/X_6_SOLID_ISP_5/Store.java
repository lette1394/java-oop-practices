package com.github.lette1394.solid.X_6_SOLID_ISP_5;

import java.util.Optional;

public interface Store {
  Optional<String> readAllText(long id);

  void save(long id, String string);
}


