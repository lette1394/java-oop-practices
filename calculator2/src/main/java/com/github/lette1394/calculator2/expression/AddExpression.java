package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.common.Contracts;
import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddExpression implements Expression {
  private final static Pattern pattern = Pattern.compile("(\\d+)+(\\d+)");
  private final Matcher matcher;
  private final String expression;

  public AddExpression(String expression) {
    this.matcher = pattern.matcher(expression);
    this.expression = expression;

    Contracts.requires(matcher.matches(), String.format("matcher requires %s", pattern.pattern()));
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {

    return null;
  }
}
