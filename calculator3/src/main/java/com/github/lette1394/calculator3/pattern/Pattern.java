package com.github.lette1394.calculator3.pattern;

@FunctionalInterface
public interface Pattern {
  String next() throws NotFoundPatternException;
}
