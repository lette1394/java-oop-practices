package com.github.lette1394.solid.X_7_SOLID_DIP_4;

import java.util.Optional;

public interface StoreReader {
  Optional<String> read(long id);
}
