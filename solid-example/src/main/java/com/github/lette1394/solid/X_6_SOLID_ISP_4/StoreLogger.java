package com.github.lette1394.solid.X_6_SOLID_ISP_4;

public interface StoreLogger {
  void saving(long id, String message);

  void saved(long id, String message);

  void reading(long id);

  void didNotFound(long id);

  void returning(long id);
}




























