package com.github.lette1394.calculator2.expression;

import static java.lang.String.format;

import com.github.lette1394.calculator2.operator.Operator;
import java.time.Duration;

public class PreemptiveTimeoutOperator implements Operator {
  private final Operator operator;
  private final Duration duration;

  public PreemptiveTimeoutOperator(Operator operator, Duration duration) {
    this.operator = operator;
    this.duration = duration;
  }

  @Override
  public Expression apply(Expression left, Expression right) throws EvaluationTimeoutException {
    return new PreemptiveTimeoutExpression(operator.apply(left, right), duration);
  }

  @Override
  public String toString() {
    return format("expression: [%s], timeout: [%s]", operator.toString(), duration.toString());
  }
}
