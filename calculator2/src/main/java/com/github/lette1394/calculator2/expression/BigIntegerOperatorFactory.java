package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.Operator;
import com.github.lette1394.calculator2.OperatorFactory;
import java.math.BigInteger;

public class BigIntegerOperatorFactory implements OperatorFactory {
  public static final OperatorFactory INSTANCE = new BigIntegerOperatorFactory();

  private BigIntegerOperatorFactory() {
  }

  @Override
  public Operator add() {
    return (left, right) -> new BigIntegerTwoOperand(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.add(right);
      }
    };
  }

  @Override
  public Operator subtract() {
    return (left, right) -> new BigIntegerTwoOperand(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.subtract(right);
      }
    };
  }

  @Override
  public Operator multiply() {
    return (left, right) -> new BigIntegerTwoOperand(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.multiply(right);
      }
    };
  }

  @Override
  public Operator divide() {
    return (left, right) -> new BigIntegerTwoOperand(left, right) {
      @Override
      protected BigInteger handle(BigInteger left, BigInteger right) {
        return left.divide(right);
      }
    };
  }
}
