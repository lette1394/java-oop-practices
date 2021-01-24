package com.github.lette1394.solid.X_7_SOLID_DIP_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class StoreCache implements IStoreCache {
  private final Map<Long, Optional<String>> cache = new HashMap<>();

  @Override
  public void save(long id, String message) {
    cache.put(id, Optional.of(message));
  }

  @Override
  public Optional<String> computeIfAbsent(long id, Supplier<Optional<String>> stringSupplier) {
    return cache.computeIfAbsent(id, __ -> stringSupplier.get());
  }
}
