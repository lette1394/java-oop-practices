package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.operator.NumericFourRulesFinder;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ParsingExpressionFactory implements ExpressionFactory {
  @Override
  public Expression of(String value) {
    return new SequentialParsingExpression(value,
      new NumericFourRulesFinder(NumericBinaryOperatorExpressionFactory.INSTANCE),
      NumericExpressionFactory.INSTANCE);
  }

  @Override
  public Expression of(long value) {
    return new SequentialParsingExpression(String.valueOf(value),
      new NumericFourRulesFinder(NumericBinaryOperatorExpressionFactory.INSTANCE),
      NumericExpressionFactory.INSTANCE);
  }

  @Override
  public Expression of(double value) {
    return new SequentialParsingExpression(String.valueOf(value),
      new NumericFourRulesFinder(NumericBinaryOperatorExpressionFactory.INSTANCE),
      NumericExpressionFactory.INSTANCE);
  }

  @Override
  public Expression of(BigInteger value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Expression of(BigDecimal value) {
    throw new UnsupportedOperationException();
  }
}
