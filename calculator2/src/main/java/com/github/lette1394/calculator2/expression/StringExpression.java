package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.result.ResultFactory.of;
import static java.lang.Long.parseLong;

import com.github.lette1394.calculator2.result.Result;
import lombok.RequiredArgsConstructor;

// FIXME (jaeeun) 2020-12-18: 이 클래스는 틀려먹었다.
//  StringExpression 이 아니라 그냥 Long.
//  StringExpression은 그 자체로 완전한 expression이 되어야 한다
//  물론 ExpressionFactory에서 조합이 된 이후에
@RequiredArgsConstructor
class StringExpression implements Expression {
  private final String value;

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    return of(parseLong(value.trim()));
  }

  @Override
  public String toString() {
    return value;
  }
}
