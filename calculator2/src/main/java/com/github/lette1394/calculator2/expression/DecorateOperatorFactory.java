package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.operator.BinaryOperator;
import com.github.lette1394.calculator2.operator.OperatorFactory;
import java.util.function.Function;

public class DecorateOperatorFactory implements OperatorFactory {
  private final Function<BinaryOperator, BinaryOperator> decorator;
  private final OperatorFactory factory;

  public DecorateOperatorFactory(
    Function<BinaryOperator, BinaryOperator> decorator,
    OperatorFactory factory) {
    this.decorator = decorator;
    this.factory = factory;
  }

  @Override
  public BinaryOperator add() {
    return decorate(decorator, factory.add());
  }

  @Override
  public BinaryOperator subtract() {
    return decorate(decorator, factory.subtract());
  }

  @Override
  public BinaryOperator multiply() {
    return decorate(decorator, factory.multiply());
  }

  @Override
  public BinaryOperator divide() {
    return decorate(decorator, factory.divide());
  }

  private BinaryOperator decorate(Function<BinaryOperator, BinaryOperator> decorator,
    BinaryOperator binaryOperator) {
    return (left, right) -> decorator.apply(binaryOperator).apply(left, right);
  }
}
