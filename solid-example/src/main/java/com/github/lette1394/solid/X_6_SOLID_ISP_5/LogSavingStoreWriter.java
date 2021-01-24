package com.github.lette1394.solid.X_6_SOLID_ISP_5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogSavingStoreWriter implements StoreWriter {
  @Override
  public void save(long id, String message) {
    log.info("Saving message [{}]", id);
  }
}
