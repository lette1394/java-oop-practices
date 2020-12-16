package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.result.OverflowException;
import com.github.lette1394.calculator2.result.Result;
import com.github.lette1394.calculator2.result.UnderflowException;

@FunctionalInterface
public interface Expression {
  Result evaluate() throws DivideByZeroException,
                           OverflowException,
                           UnderflowException,
                           EvaluationTimeoutException;
}
