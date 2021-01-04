package com.github.lette1394.calculator3.pattern;

@FunctionalInterface
public interface PatternFinder {
  String find(String expression) throws NotFoundPatternException;
}
