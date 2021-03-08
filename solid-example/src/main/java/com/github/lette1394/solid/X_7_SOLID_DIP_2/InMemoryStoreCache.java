package com.github.lette1394.solid.X_7_SOLID_DIP_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryStoreCache implements StoreCache, StoreWriter {
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
 