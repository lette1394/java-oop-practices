package com.github.lette1394.calculator3.pattern;

import java.util.List;

public class RealNumericPatternFinder implements PatternFinder {
  private final PatternFinder patternFinder;

  public RealNumericPatternFinder() {
    this.patternFinder = new FirstMatchedPatternFinder(List.of(
      new DecimalPatternFinder(),
      new E_NotationBasedDecimalPatternFinder(),
      new IntegerPatternFinder()));
  }

  @Override
  public String find(String expression) throws NotFoundPatternException {
    return patternFinder.find(expression);
  }
}
