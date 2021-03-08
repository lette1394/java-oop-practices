package com.github.lette1394.solid.X_7_SOLID_DIP_4;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryStoreCache implements StoreWriter, StoreReader {
  private final Map<Long, String> cache = new HashMap<>();
  private final StoreWriter storeWriter;
  private final StoreReader storeReader;

  @Override
  public void save(long id, String message) {
    storeWriter.save(id, message); // super.save(id, message)
    cache.put(id, message);
  }

  @Override
  public Optional<String> read(long id) {
    if (cache.containsKey(id)) {
      return Optional.of(cache.get(id));
    }

    final Optional<String> result = storeReader.read(id);
    result.ifPresent(message -> cache.put(id, message));
    return result;
  }
}
