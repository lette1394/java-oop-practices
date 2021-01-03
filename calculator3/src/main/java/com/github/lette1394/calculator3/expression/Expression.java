package com.github.lette1394.calculator3.expression;

@FunctionalInterface
public interface Expression {
  String evaluate() throws DivideByZeroException,
                           OverflowException,
                           UnderflowException,
                           EvaluationTimeoutException;
}
