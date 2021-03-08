package com.github.lette1394.solid.X_7_SOLID_DIP_3;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompositeStoreWriter implements StoreWriter {
  private final List<StoreWriter> writers;

  @Override
  public void save(long id, String message) {
    for (StoreWriter writer : writers) {
      writer.save(id, message);
    }
  }
}
