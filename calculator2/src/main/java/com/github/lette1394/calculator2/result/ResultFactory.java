package com.github.lette1394.calculator2.result;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class ResultFactory {
  public static Result of(BigInteger bigInteger) {
    return new BigIntegerResult(bigInteger);
  }

  public static Result of(BigDecimal bigDecimal) {
    return new BigDecimalResult(bigDecimal);
  }

  public static Result of(Number number) {
    return new NumberResult(number);
  }
}
