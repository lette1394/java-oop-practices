package com.github.lette1394.calculator2.common;

import java.util.Objects;

public final class ExceptionUtils {
  private ExceptionUtils() {
  }

  public static RuntimeException throwAsUncheckedException(Throwable throwable) {
    Contracts.requires(Objects.nonNull(throwable), "throwable requires non-null");
    ExceptionUtils.throwAs(throwable);

    // Make the compiler and ide happy: the following line will never be executed.
    return Objects.requireNonNull(null);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Throwable> void throwAs(Throwable t) throws T {
    throw (T) t;
  }
}
