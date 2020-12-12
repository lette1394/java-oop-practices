package com.github.lette1394.numberbaseball;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Digits {
  private final List<Digit> digits;

  public static Digits of(int... ints) {
    return new Digits(IntStream
      .range(0, ints.length)
      .mapToObj(i -> new Digit(i, ints[i]))
      .collect(Collectors.toUnmodifiableList()));
  }

  public Digits strikes(Digits other) {
    return match(digit -> other
      .sameValue(digit)
      .sameIndex(digit));
  }

  public Digits balls(Digits other) {
    return match(digit -> other
      .sameValue(digit)
      .differentIndex(digit));
  }

  public long count() {
    return digits.size();
  }

  public List<Digit> asList() {
    return digits;
  }

  private Digits sameIndex(Digit other) {
    return filter(digit -> other.matchIndex(digit));
  }

  private Digits differentIndex(Digit other) {
    return filter(digit -> !other.matchIndex(digit));
  }

  private Digits sameValue(Digit other) {
    return filter(digit -> other.matchValue(digit));
  }

  private Digits match(Function<Digit, Digits> function) {
    return new Digits(digits.stream()
      .map(function)
      .flatMap(digits -> digits.stream())
      .collect(Collectors.toUnmodifiableList()));
  }

  private Digits filter(Predicate<Digit> predicate) {
    return new Digits(digits.stream()
      .filter(predicate)
      .collect(Collectors.toUnmodifiableList()));
  }

  private Stream<Digit> stream() {
    return digits.stream();
  }

  @Override
  public String toString() {
    return digits
      .stream()
      .map(digit -> digit.getValue())
      .map(value -> "" + value)
      .collect(Collectors.joining(""));
  }
}
