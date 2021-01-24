package com.github.lette1394.solid.X_6_SOLID_ISP_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class StoreCache {
  private final Map<Long, Optional<String>> cache = new HashMap<>();

  public void put(long id, String message) {
    cache.put(id, Optional.of(message));
  }

  public Optional<String> computeIfAbsent(long id, Supplier<Optional<String>> stringSupplier) {
    return cache.computeIfAbsent(id, __ -> stringSupplier.get());
  }
}
