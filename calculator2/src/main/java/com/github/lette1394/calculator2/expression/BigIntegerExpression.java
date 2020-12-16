package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;

import com.github.lette1394.calculator2.result.Result;
import java.math.BigInteger;

// FIXME (jaeeun) 2020-12-16: 이거 BigDecimal 용으로도 사용가능하게
class BigIntegerExpression implements Expression {
  private final BigInteger value;

  public BigIntegerExpression(BigInteger value) {
    this.value = value;
  }

  public BigIntegerExpression(String value) {
    this(new BigInteger(value));
  }

  public BigIntegerExpression(long value) {
    this(Long.toString(value));
  }

  @Override
  public Result evaluate() {
    return of(value);
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
