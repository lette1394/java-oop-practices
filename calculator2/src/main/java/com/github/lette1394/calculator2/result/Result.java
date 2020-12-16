package com.github.lette1394.calculator2.result;

public interface Result {
  long asLong();

  // TODO: remove method
  //  이거는 configurable 설정으로 풀어야 한다.
  //  overflow/underflow를 허용 -> asLong() 호출 성공
  //  overflow/underflow를 금지 -> Expression.evaluate() 에서 예외 발생
  long asLongExact();

  double asDouble();

  String asString();
}
