package com.github.lette1394.solid.X_7_SOLID_DIP_3;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("DuplicatedCode")
@Slf4j
@RequiredArgsConstructor
public class Slf4jStoreLogger implements StoreWriter, StoreReader {
  private final StoreWriter writer;
  private final StoreReader reader;

  // AOP: aspect of programming
  // round advice
  @Override
  public void save(long id, String message) {
    log.info("Saving message [{}]", id);
    writer.save(id, message);
    log.info("Saved message [{}]", id);
  }

  @Override
  public Optional<String> read(long id) {
    log.debug("Reading message: [{}]", id);
    final Optional<String> result = reader.read(id);
    if (result.isPresent()) {
      log.debug("Returning message [{}]", id);
    } else {
      log.debug("No message [{}] found", id);
    }

    return result;
  }
}




























