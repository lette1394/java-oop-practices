package com.github.lette1394.calculator3.expression;

@FunctionalInterface
public interface Evaluator {
  String evaluate(String expression) throws UnsupportedExpressionException,
                                            DivideByZeroException,
                                            OverflowException,
                                            UnderflowException,
                                            EvaluationTimeoutException;
}
