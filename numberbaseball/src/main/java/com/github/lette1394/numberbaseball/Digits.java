package com.github.lette1394.numberbaseball;

import com.google.common.collect.Streams;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Digits {
  private final int[] value;
  private final Stream<Digit> stream;

  public int strikes(Digits other) {
    return (int) Streams
      .zip(stream,
           other.stream,
           (digit1, digit2) -> digit1.matchValue(digit2) && digit1.matchIndex(digit2))
      .filter(aBoolean -> aBoolean)
      .count();
  }

  public int balls(Digits other) {
    int ret = 0;

    for (int i = 0; i < other.value.length; i++) {
      for (int j = 0; j < other.value.length; j++) {
        if (value[i] == other.value[j] && i != j) {
          ret++;
        }
      }
    }
    return ret;
  }
}
