package com.github.lette1394.numberbaseball;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Result {
  int strike;
  int ball;
}
