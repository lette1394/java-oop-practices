package com.github.lette1394.calculator3.evaluator;

public class TrimmedAdder implements Adder {
  private final Adder adder;

  private TrimmedAdder(Adder adder) {
    this.adder = adder;
  }

  public static Adder trim(Adder adder) {
    return new TrimmedAdder(adder);
  }

  @Override
  public String add(String left, String right) {
    return adder.add(left.trim(), right.trim());
  }
}
