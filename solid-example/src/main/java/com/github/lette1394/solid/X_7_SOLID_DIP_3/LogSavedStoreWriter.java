package com.github.lette1394.solid.X_7_SOLID_DIP_3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogSavedStoreWriter implements StoreWriter {
  @Override
  public void save(long id, String message) {
    log.info("Saved message [{}]", id);
  }
}
