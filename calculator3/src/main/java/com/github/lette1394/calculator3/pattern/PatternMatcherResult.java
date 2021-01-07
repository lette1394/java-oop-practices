package com.github.lette1394.calculator3.pattern;

import java.util.Optional;

// TODO: rename: MatchedPattern or sth good
@FunctionalInterface
public interface PatternMatcherResult {
  Optional<String> next();
}
