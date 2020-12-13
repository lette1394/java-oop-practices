package com.github.lette1394.ladder;

public class Contracts {
  public static void requires(boolean condition, String message) {
    if (condition) {
      return;
    }

    throw new ContractsViolationException(message);
  }
}
