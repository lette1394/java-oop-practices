package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.Contracts;
import com.github.lette1394.calculator2.Operator;
import com.github.lette1394.calculator2.OperatorFinder;
import com.github.lette1394.calculator2.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

class SequentialParsingExpression implements Expression {
  private final String expression;
  private final Matcher matcher;
  private final OperatorFinder operatorFinder;
  private final ExpressionFactory expressionFactory;

  private Result cache;

  public SequentialParsingExpression(String expression, OperatorFinder operatorFinder,
    ExpressionFactory expressionFactory) {
    this.expression = expression;
    this.matcher = Pattern.compile("^-\\d+|\\d+|[/*+\\-]").matcher(expression);
    this.operatorFinder = operatorFinder;
    this.expressionFactory = expressionFactory;
  }

  @Override
  public Result evaluate() {
    // cache가 없으면 결과가 매번 달라짐 (자체적으로 상태를 들고 있기 때문에 한 번만 평가되어야 한다)
    if (cache == null) {
      return cache = parse().evaluate();
    }
    return cache;
  }

  private Expression parse() {
    if (StringUtils.isNumericSpace(expression)) {
      return expressionFactory.of(expression);
    }
    return sequentialParse();
  }

  private Expression sequentialParse() {
    Expression acc = initialOperand();

    while (hasNext()) {
      acc = nextOperator().apply(acc, getNextOperand());
    }
    return acc;
  }

  private Expression initialOperand() {
    return getNextOperand();
  }

  private boolean hasNext() {
    return !matcher.hitEnd();
  }

  private boolean findNext() {
    return matcher.find();
  }

  private Operator nextOperator() {
    Contracts.requires(findNext(), "cannot find next operator");
    final String key = trim(matcher.group());

    return operatorFinder.find(key);
  }

  private Expression getNextOperand() {
    Contracts.requires(findNext(), "cannot find next operand");
    return expressionFactory.of(trim(matcher.group()));
  }

  private String trim(String value) {
    return value.trim();
  }
}