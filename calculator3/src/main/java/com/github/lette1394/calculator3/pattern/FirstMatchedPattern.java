package com.github.lette1394.calculator3.pattern;

import java.util.List;

public class FirstMatchedPattern implements MatchedPattern {
  private final List<MatchedPattern> patterns;

  public FirstMatchedPattern(List<MatchedPattern> patterns) {
    this.patterns = patterns;
  }

  @Override
  public String next() throws NotMatchedAnymoreException {
    final NotMatchedAnymoreException exception = new NotMatchedAnymoreException();
    for (MatchedPattern pattern : patterns) {
      try {
        return pattern.next();
      } catch (NotMatchedAnymoreException e) {
        exception.addSuppressed(e);
      }
    }
    throw exception;
  }
}
