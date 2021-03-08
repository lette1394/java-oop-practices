package com.github.lette1394.solid.X_6_SOLID_ISP_5;

@FunctionalInterface
public interface StoreWriter {
  void save(long id, String message);
}
