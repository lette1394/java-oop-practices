package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.operator.OperatorFinder;
import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParenthesesParsingExpression implements Expression {
  private final String expression;
  private final Matcher matcher;
  private final ExpressionFactory expressionFactory;

  public ParenthesesParsingExpression(
    String expression,
    OperatorFinder operatorFinder,
    ExpressionFactory expressionFactory) {
    this.expression = expression;
    this.matcher = Pattern.compile("(.+)").matcher(expression);
    this.expressionFactory = expressionFactory;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {

    return null;
  }
}
