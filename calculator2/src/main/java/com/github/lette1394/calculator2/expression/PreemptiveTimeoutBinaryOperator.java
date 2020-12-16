package com.github.lette1394.calculator2.expression;

import static java.lang.String.format;

import com.github.lette1394.calculator2.operator.BinaryOperator;
import java.time.Duration;

public class PreemptiveTimeoutBinaryOperator implements BinaryOperator {
  private final BinaryOperator binaryOperator;
  private final Duration duration;

  public PreemptiveTimeoutBinaryOperator(BinaryOperator binaryOperator, Duration duration) {
    this.binaryOperator = binaryOperator;
    this.duration = duration;
  }

  @Override
  public Expression apply(Expression left, Expression right) throws EvaluationTimeoutException {
    return new PreemptiveTimeoutExpression(binaryOperator.apply(left, right), duration);
  }

  @Override
  public String toString() {
    return format("expression: [%s], timeout: [%s]", binaryOperator, duration);
  }
}
