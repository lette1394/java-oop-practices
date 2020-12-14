package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Expressions.of;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class SequentialParsingExpression implements Expression {
  private final Matcher operandMatcher;
  private final Matcher operatorMatcher;
  private final String value;

  private final Map<String, Operator> operators = new HashMap<>();

  private Result cache;

  public SequentialParsingExpression(String value) {
    this.value = value;
    this.operandMatcher = Pattern.compile("\\d+").matcher(value);
    this.operatorMatcher = Pattern.compile("[/*+\\-]").matcher(value);

    operators.put("+", Operators.add());
    operators.put("-", Operators.subtract());
    operators.put("*", Operators.multiply());
    operators.put("/", Operators.divide());
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
    if (StringUtils.isNumericSpace(value)) {
      return of(value);
    }
    return sequentialParse();
  }

  private Expression sequentialParse() {
    Expression acc = initialOperand();
    while (findNextOperand()) {
      acc = nextOperator().apply(acc, getNextOperand());
    }
    return acc;
  }

  private Expression initialOperand() {
    Contracts.requires(findNextOperand(), "cannot found next operand");
    return getNextOperand();
  }

  private Operator nextOperator() {
    Contracts.requires(findNextOperator(), "cannot found next operator");
    final String key = trim(operatorMatcher.group());
    if (operators.containsKey(key)) {
      return operators.get(key);
    }
    throw new IllegalStateException(String.format("[%s] operator cannot be supported", key));
  }

  private boolean findNextOperator() {
    return operatorMatcher.find();
  }

  private boolean findNextOperand() {
    return operandMatcher.find();
  }

  private Expression getNextOperand() {
    return of(trim(operandMatcher.group()));
  }

  private String trim(String value) {
    return value.trim();
  }
}