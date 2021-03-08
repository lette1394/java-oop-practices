package com.github.lette1394.solid.X_7_SOLID_DIP_5.message;

import java.util.Optional;

public interface StoreReader {
  Optional<String> read(long id);
}
