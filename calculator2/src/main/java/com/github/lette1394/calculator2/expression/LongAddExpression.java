package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;
import static java.lang.Long.parseLong;

import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Pattern;

public class LongAddExpression extends MatchedTwoOperandExpression<Long, Long> {
  private final static Pattern pattern = Pattern.compile("\\s*(\\d+)\\s*(\\+)\\s*(\\d+)\\s*");
  private final String expression;

  public LongAddExpression(String expression) throws UnsupportedOperationException {
    super(pattern.matcher(expression));
    this.expression = expression;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    return of(left() + right());
  }

  @Override
  protected Long toLeft(String left) {
    return parseLong(left);
  }

  @Override
  protected Long toRight(String right) {
    return parseLong(right);
  }

  @Override
  public String toString() {
    return expression;
  }
}
