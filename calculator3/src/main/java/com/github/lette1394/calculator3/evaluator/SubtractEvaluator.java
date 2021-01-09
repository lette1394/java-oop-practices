package com.github.lette1394.calculator3.evaluator;

import com.github.lette1394.calculator3.pattern.PatternMatcher;

public class SubtractEvaluator extends TwoOperandEvaluator {
  private final Subtractor subtractor;

  public SubtractEvaluator(PatternMatcher patternMatcher, Subtractor subtractor) {
    super(patternMatcher, "\\-");
    this.subtractor = subtractor;
  }

  @Override
  protected String evaluate(String left, String right) throws UnsupportedExpressionException,
    DivideByZeroException,
    OverflowException,
    UnderflowException,
    EvaluationTimeoutException {
    return subtractor.subtract(left, right);
  }

  @Override
  public String toString() {
    return "";
  }
}
