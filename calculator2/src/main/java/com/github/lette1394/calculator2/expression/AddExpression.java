package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Pattern;

public class AddExpression extends MatchedTwoOperandExpression {
  private final static Pattern pattern = Pattern.compile(
    "\\s*(\\d+\\.?\\d*)\\s*(\\+)\\s*(\\d+\\.?\\d*)\\s*"
  );
  private final String expression;

  public AddExpression(String expression) throws UnsupportedOperationException {
    super(pattern.matcher(expression));
    this.expression = expression;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    if (isDecimal(left()) || isDecimal(right())) {
      return of(parseDouble(left()) + parseDouble(right()));
    }
    return of(parseLong(toInteger(left())) + parseLong(toInteger(right())));
  }

  @Override
  public String toString() {
    return expression;
  }
}
