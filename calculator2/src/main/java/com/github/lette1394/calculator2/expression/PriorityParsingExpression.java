package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.Operator;
import com.github.lette1394.calculator2.OperatorFinder;
import com.github.lette1394.calculator2.result.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

// FIXME (jaeeun) 2020-12-16: 의존성 제거 // CalculatorBuilder 등으로
public class PriorityParsingExpression implements Expression {
  private final String value;
  private final Matcher matcher;
  private final OperatorFinder operatorFinder;
  private final ExpressionFactory expressionFactory;

  private Result cache;

  public PriorityParsingExpression(
    String value,
    OperatorFinder operatorFinder,
    ExpressionFactory expressionFactory) {
    this.value = value;
    this.matcher = Pattern.compile("(.*)(\\+|- )(.*)").matcher(value);
    this.operatorFinder = operatorFinder;
    this.expressionFactory = expressionFactory;
  }

  @Override
  public Result evaluate() {
    if (cache == null) {
      return cache = parse().evaluate();
    }
    return cache;
  }

  private Expression parse() {
    if (StringUtils.isNumericSpace(value)) {
      return expressionFactory.of(value);
    }
    if (matcher.matches()) {
      return operator().apply(left(), right());
    }
    return new SequentialParsingExpression(value, operatorFinder, expressionFactory);
  }

  private Expression left() {
    return new SequentialParsingExpression(trim(matcher.group(1)), operatorFinder,
      expressionFactory);
  }

  private Operator operator() {
    return operatorFinder.find(trim(matcher.group(2)));
  }

  private Expression right() {
    return new SequentialParsingExpression(trim(matcher.group(3)), operatorFinder,
      expressionFactory);
  }

  private String trim(String value) {
    return value.trim();
  }
}
