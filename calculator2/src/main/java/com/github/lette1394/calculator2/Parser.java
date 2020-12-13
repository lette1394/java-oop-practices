package com.github.lette1394.calculator2;

import static com.github.lette1394.calculator2.Expressions.of;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
  private final static Pattern pattern = Pattern.compile("^(.+)([+-])(.+)$");
  private final Matcher matcher;
  private final String value;

  private final Map<String, Operator> operators = new HashMap<>();

  private Parser(String value) {
    this.value = value;
    this.matcher = pattern.matcher(value);

    operators.put("+", Operators.add());
    operators.put("-", Operators.subtract());
  }

  public static Parser parse(String value) {
    return new Parser(value);
  }

  public Expression left() {
    return of(trim(parse(1)));
  }

  public Operator operator() {
    final String key = trim(parse(2));
    if (operators.containsKey(key)) {
      return operators.get(key);
    }
    throw new IllegalStateException(String.format("[%s] operator cannot be supported", key));
  }

  public Expression right() {
    return of(trim(parse(3)));
  }

  private String trim(String value) {
    return value.trim();
  }

  private String parse(int groupIndex) {
    if (matcher.matches()) {
      return matcher.group(groupIndex);
    }
    return value;
  }
}
