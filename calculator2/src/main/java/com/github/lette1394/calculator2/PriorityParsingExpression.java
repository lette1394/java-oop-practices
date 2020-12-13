package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Expressions.of;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class PriorityParsingExpression implements Expression {
  private final String value;

  private final Matcher matcher;
  private final Map<String, Operator> operators = new HashMap<>();


  public PriorityParsingExpression(String value) {
    this.value = value;
    // FIXME (jaeeun) 2020-12-14:
    //  편법으로 구현되어 있다... 음.. 그런데 잘 살리면 되게 쉽게 가능할지도?
    this.matcher = Pattern.compile("(.*)([+\\-])(.*)").matcher(value);

    operators.put("-", Operators.subtract());
    operators.put("+", Operators.add());
  }

  @Override
  public long evaluate() {
    return parse().evaluate();
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
    return operators.get(trim(matcher.group(2)));
  }

  private Expression right() {
    return new SequentialParsingExpression(trim(matcher.group(3)));
  }

  private String trim(String value) {
    return value.trim();
  }
}
