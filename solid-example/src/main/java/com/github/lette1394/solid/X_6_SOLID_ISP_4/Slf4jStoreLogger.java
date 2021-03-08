package com.github.lette1394.solid.X_6_SOLID_ISP_4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jStoreLogger implements StoreLogger {
  public void saving(long id, String message) {
    log.info("Saving message [{}]", id);
  }

  public void saved(long id, String message) {
    log.info("Saved message [{}]", id);
  }

  public void reading(long id) {
    log.debug("Reading message: [{}]", id);
  }

  public void didNotFound(long id) {
    log.debug("No message [{}] found", id);
  }

  public void returning(long id) {
    log.debug("Returning message [{}]", id);
  }
}
