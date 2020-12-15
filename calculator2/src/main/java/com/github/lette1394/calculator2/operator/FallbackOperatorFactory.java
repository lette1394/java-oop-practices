package com.github.lette1394.calculator2.operator;

import com.github.lette1394.calculator2.UnrecoverableException;

public class FallbackOperatorFactory implements OperatorFactory {
  private final OperatorFactory factory;
  private final OperatorFactory fallbackFactory;

  public FallbackOperatorFactory(
    OperatorFactory factory,
    OperatorFactory fallbackFactory) {
    this.factory = factory;
    this.fallbackFactory = fallbackFactory;
  }

  @Override
  public Operator add() {
    return installFallback(factory.add(), fallbackFactory.add());
  }

  @Override
  public Operator subtract() {
    return installFallback(factory.subtract(), fallbackFactory.subtract());
  }

  @Override
  public Operator multiply() {
    return installFallback(factory.multiply(), fallbackFactory.multiply());
  }

  @Override
  public Operator divide() {
    return installFallback(factory.divide(), fallbackFactory.divide());
  }

  private Operator installFallback(Operator operator, Operator fallback) {
    return (left, right) -> () -> {
      try {
        return operator.apply(left, right).evaluate();
      } catch (UnrecoverableException unrecoverableException) {
        throw unrecoverableException;
      } catch (Exception e) {
        return fallback.apply(left, right).evaluate();
      }
    };
  }
}
