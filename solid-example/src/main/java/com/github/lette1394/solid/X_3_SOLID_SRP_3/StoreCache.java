package com.github.lette1394.solid.X_3_SOLID_SRP_3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class StoreCache {
  private final Map<Long, String> cache = new HashMap<>();

  public void put(long id, String message) {
    cache.put(id, message);
  }

  public String computeIfAbsent(long id, Supplier<String> stringSupplier) {
    return cache.computeIfAbsent(id, __ -> stringSupplier.get());
  }
}
