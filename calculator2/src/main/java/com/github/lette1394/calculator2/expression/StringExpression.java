package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.Results.of;
import static java.lang.Long.parseLong;

import com.github.lette1394.calculator2.result.Result;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class StringExpression implements Expression {
  private final String value;

  @Override
  public Result evaluate() {
    return of(parseLong(value.trim()));
  }
}
