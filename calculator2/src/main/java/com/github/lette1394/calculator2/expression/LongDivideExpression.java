package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;
import static java.lang.String.format;

import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Pattern;

public class LongDivideExpression extends LongExpression {
  private final static Pattern pattern = Pattern
    .compile("\\s*(\\d+\\.?\\d*)\\s*(/)\\s*(\\d+\\.?\\d*)\\s*");
  private final String expression;

  public LongDivideExpression(String expression) throws UnsupportedOperationException {
    super(pattern.matcher(expression));
    this.expression = expression;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    final long left = Long.parseLong(left());
    final long right = Long.parseLong(right());

    if (right == 0) {
      throw new DivideByZeroException(format("%s / %s", left, right));
    }
    if (right % 10 == 0) {
      return of(left / right);
    }
    return of(left / right);
  }

  @Override
  public String toString() {
    return expression;
  }
}
