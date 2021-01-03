package com.github.lette1394.calculator3.expression;

@FunctionalInterface
public interface Expression {
  String evaluate(String expression) throws UnsupportedOperationException,
                                            DivideByZeroException,
                                            OverflowException,
                                            UnderflowException,
                                            EvaluationTimeoutException;
}
