package com.github.lette1394.calculator2;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ExpressionFactory {
  Expression of(String value);

  Expression of(long value);

  Expression of(double value);

  Expression of(BigInteger value);

  Expression of(BigDecimal value);
}
