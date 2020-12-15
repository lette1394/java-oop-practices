package com.github.lette1394.calculator2.result;

public interface Result {
  long asLong();

  long asLongExact();

  double asDouble();

  String asString();
}
