package com.github.lette1394.calculator3.evaluator;

public class TrimmedSubtractor implements Subtractor {
  private final Subtractor subtractor;

  private TrimmedSubtractor(Subtractor subtractor) {
    this.subtractor = subtractor;
  }

  public static Subtractor trim(Subtractor subtractor) {
    return new TrimmedSubtractor(subtractor);
  }

  @Override
  public String subtract(String left, String right) {
    return subtractor.subtract(left.trim(), right.trim());
  }
}
