package com.github.lette1394.numberbaseball;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalDigit {
  private final Optional<Digit> value;

  private OptionalDigit(Optional<Digit> value) {
    Contracts.requires(Objects.nonNull(value), "requires nonNull");
    this.value = value;
  }

  public static OptionalDigit of(Digit digit) {
    return new OptionalDigit(Optional.of(digit));
  }

  public static OptionalDigit empty() {
    return new OptionalDigit(Optional.empty());
  }

//  public OptionalDigit matchIndex(Digit other) {
//    return value
//      .map(digit -> digit.matchIndex(other))
//      .orElseGet(() -> empty());
//  }
//
//  public OptionalDigit matchValue(Digit other) {
//    return value
//      .map(digit -> digit.matchValue(other))
//      .orElseGet(() -> empty());
//  }

  public Stream<Digit> rawStream() {
    return value.stream();
  }
}
