package com.github.lette1394.calculator3.evaluator;

import static java.lang.String.format;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FallbackEvaluator implements Evaluator {
  private final List<Evaluator> evaluators;

  @Override
  public String evaluate(String expression) throws
                                            UnsupportedExpressionException,
                                            DivideByZeroException,
                                            OverflowException,
                                            UnderflowException,
                                            EvaluationTimeoutException {
    final RuntimeException exception = new UnsupportedExpressionException(
      format("Not supported expression:[%s]", expression));
    for (Evaluator evaluator : evaluators) {
      try {
        return evaluator.evaluate(expression);
      } catch (UnsupportedExpressionException suppressed) {
        exception.addSuppressed(suppressed);
      }
    }
    throw exception;
  }
}
