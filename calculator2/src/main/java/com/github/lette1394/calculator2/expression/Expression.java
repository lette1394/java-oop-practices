package com.github.lette1394.calculator2.expression;

import com.github.lette1394.calculator2.DivideByZeroException;
import com.github.lette1394.calculator2.Result;

@FunctionalInterface
public interface Expression {
  Result evaluate() throws DivideByZeroException;
}
