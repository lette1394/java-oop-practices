package com.github.lette1394.calculator3.pattern;

@FunctionalInterface
public interface MatchedPattern {
  String next() throws NotMatchedAnymoreException;
}
