package com.github.lette1394.solid.X_5_SOLID_LSP_2;

import java.io.File;
import java.util.Optional;

// header interface
public interface Store {
  Optional<String> readAllText(long id); // 추상화 레벨을 맞췄다. (id 사용)

  void writeAllText(long id, String string);

  File getFile(long id);
}


