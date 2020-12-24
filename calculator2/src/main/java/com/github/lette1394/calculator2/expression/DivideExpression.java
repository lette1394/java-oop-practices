package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import static java.lang.String.format;

import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Pattern;

public class DivideExpression extends LongExpression {
  private final static Pattern pattern = Pattern
    .compile("\\s*(\\d+\\.?\\d*)\\s*(/)\\s*(\\d+\\.?\\d*)\\s*");
  private final String expression;

  public DivideExpression(String expression) throws UnsupportedOperationException {
    super(pattern.matcher(expression));
    this.expression = expression;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    if (isDecimal(left()) || isDecimal(right())) {
      final double left = parseDouble(left());
      final double right = parseDouble(right());
      if (Double.compare(right, 0) == 0) {
        throw new DivideByZeroException(expression);
      }

      return of(left / right);
    }

    final long left = parseLong(left());
    final long right = parseLong(right());
    if (right == 0) {
      throw new DivideByZeroException(format("%s / %s", left, right));
    }
    if (left % right == 0) {
      return of(left / right);
    }
    return of((double) left / right);
  }

  @Override
  public String toString() {
    return expression;
  }
}
