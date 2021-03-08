package com.github.lette1394.solid.X_7_SOLID_DIP_2;

import java.util.Optional;
import java.util.function.Supplier;

public interface StoreCache {
  void save(long id, String message);

  Optional<String> computeIfAbsent(long id, Supplier<Optional<String>> stringSupplier);
}
