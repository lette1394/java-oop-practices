package com.github.lette1394.solid.X_6_SOLID_ISP_4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogSavingStoreWriter {
  public void save(long id, String message) {
    log.info("Saving message [{}]", id);
  }
}
