package com.github.lette1394.calculator2;

public interface Operator extends Expression {
  Expression apply(Expression left, Expression right);
}
