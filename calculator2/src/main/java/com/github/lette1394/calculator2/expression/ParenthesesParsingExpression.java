package com.github.lette1394.calculator2.expression;

import static java.lang.String.format;

import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParenthesesParsingExpression implements Expression {
  private final String expression;

  // FIXME (jaeeun) 2020-12-18: 왠지 Matcher가지고 하나 클래스가 나올 거 같다...
  private final Matcher matcher;
  private final ExpressionFactory expressionFactory;

  public ParenthesesParsingExpression(
    String expression,
    ExpressionFactory expressionFactory) {
    this.expression = expression;
    this.matcher = Pattern.compile("(.*)(\\(.+\\))(.*)").matcher(expression);
    this.expressionFactory = expressionFactory;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    return parse(expression).evaluate();
  }

  private Expression parse(String expression) {
    if (matcher.find()) {
      return parse(format("%s%s%s", left(), inner(), right()));
    }
    return expressionFactory.of(expression);
  }

  private String left() {
    return matcher.group(1);
  }

  private String inner() {
    return expressionFactory.of(unwrapParentheses(matcher.group(2))).evaluate().asString();
  }

  private String right() {
    return matcher.group(3);
  }

  private String unwrapParentheses(String value) {
    final int length = value.length();
    return value.substring(1, length - 1);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
