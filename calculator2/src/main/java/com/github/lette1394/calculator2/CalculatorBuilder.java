package com.github.lette1394.calculator2;

import java.time.Duration;

public interface CalculatorBuilder {
  CalculatorBuilder timeout(Duration duration);

  Calculator build();
}
