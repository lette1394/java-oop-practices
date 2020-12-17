package com.github.lette1394.calculator2.expression;

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

  private static String unwrapParentheses(String value) {
    final int length = value.length();
    return value.substring(1, length - 1);
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
      final String left = matcher.group(1);
      final Expression inner = expressionFactory.of(unwrapParentheses(matcher.group(2)));
      final String right = matcher.group(3);
      return parse(String.format("%s%s%s",
        left, inner.evaluate().asString(), right));
    }
    return expressionFactory.of(expression);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
