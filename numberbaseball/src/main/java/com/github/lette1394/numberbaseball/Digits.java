package com.github.lette1394.numberbaseball;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class Digits {
  private final List<Digit> digits;

  public static Digits of(int[] ints) {
    return new Digits(IntStream
      .range(0, ints.length)
      .mapToObj(i -> new Digit(i, ints[i]))
      .collect(Collectors.toUnmodifiableList()));
  }

  public int strikes(Digits other) {
    return (int) digits.stream()
      .map(digit -> other
        .matchIndex(digit)
        .matchValue(digit))
      .flatMap(digits -> digits.stream())
      .count();
  }

  public int balls(Digits other) {
    return (int) digits.stream()
      .map(digit -> other
        .notMatchIndex(digit)
        .matchValue(digit))
      .flatMap(digits -> digits.stream())
      .count();
  }

  private Digits notMatchIndex(Digit other) {
    return new Digits(digits
      .stream()
      .filter(digit -> !digit.matchIndex(other))
      .collect(Collectors.toUnmodifiableList()));
  }

  private Digits matchIndex(Digit other) {
    return new Digits(digits
      .stream()
      .filter(digit -> digit.matchIndex(other))
      .collect(Collectors.toUnmodifiableList()));
  }

  private Digits matchValue(Digit other) {
    return new Digits(digits
      .stream()
      .filter(digit -> digit.matchValue(other))
      .collect(Collectors.toUnmodifiableList()));
  }

  private Stream<Digit> stream() {
    return digits.stream();
  }
}
