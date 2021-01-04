package com.github.lette1394.calculator3.pattern;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FirstMatchedPatternFinder implements PatternFinder {
  private final List<PatternFinder> finders;

  @Override
  public String find(String expression) throws NotFoundPatternException {
    final NotFoundPatternException exception = new NotFoundPatternException();
    for (PatternFinder finder : finders) {
      try {
        return finder.find(expression);
      } catch (NotFoundPatternException e) {
        exception.addSuppressed(e);
      }
    }
    throw exception;
  }
}
