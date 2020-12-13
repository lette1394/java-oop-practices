package com.github.lette1394.calculator;

import java.util.List;

public interface Parser<T> {
  List<T> parse(String expression);
}
