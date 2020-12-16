package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.operator.Operator;
import com.github.lette1394.calculator2.operator.OperatorFactory;
import java.util.function.Function;

public class DecorateOperatorFactory implements OperatorFactory {
  private final Function<Operator, Operator> decorator;
  private final OperatorFactory factory;

  public DecorateOperatorFactory(
    Function<Operator, Operator> decorator,
    OperatorFactory factory) {
    this.decorator = decorator;
    this.factory = factory;
  }

  @Override
  public Operator add() {
    return decorate(decorator, factory.add());
  }

  @Override
  public Operator subtract() {
    return decorate(decorator, factory.subtract());
  }

  @Override
  public Operator multiply() {
    return decorate(decorator, factory.multiply());
  }

  @Override
  public Operator divide() {
    return decorate(decorator, factory.divide());
  }

  private Operator decorate(Function<Operator, Operator> decorator, Operator operator) {
    return (left, right) -> decorator.apply(operator).apply(left, right);
  }
}
