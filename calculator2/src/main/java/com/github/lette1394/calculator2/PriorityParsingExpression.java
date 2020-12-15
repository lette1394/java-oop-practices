package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Expressions.of;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class PriorityParsingExpression implements Expression {
  private final String value;
  private final Matcher matcher;
  private final OperatorFinder operatorFinder;

  private Result cache;

  public PriorityParsingExpression(String value,
                                   OperatorFinder operatorFinder) {
    this.value = value;
    this.matcher = Pattern.compile("(.*)(\\+|- )(.*)").matcher(value);
    this.operatorFinder = operatorFinder;
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
      return of(value);
    }
    if (matcher.matches()) {
      return operator().apply(left(), right());
    }
    return new SequentialParsingExpression(value);
  }

  private Expression left() {
    return new SequentialParsingExpression(trim(matcher.group(1)));
  }

  private Operator operator() {
    return operatorFinder.find(trim(matcher.group(2)));
  }

  private Expression right() {
    return new SequentialParsingExpression(trim(matcher.group(3)));
  }

  private String trim(String value) {
    return value.trim();
  }
}
