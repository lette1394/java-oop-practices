package com.github.lette1394.solid.X_6_SOLID_ISP_1;

import java.util.Optional;

public interface Store {
  Optional<String> readAllText(long id);

  void writeAllText(long id, String string);
}


