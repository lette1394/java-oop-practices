package com.github.lette1394.calculator2.expression;

import static java.lang.String.format;

import com.github.lette1394.calculator2.common.ExceptionUtils;
import com.github.lette1394.calculator2.result.Result;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedExpression implements Expression {
  private final Expression expression;
  private final Duration delay;

  public DelayedExpression(Expression expression, Duration delay) {
    this.expression = expression;
    this.delay = delay;
  }

  @Override
  public Result evaluate() throws DivideByZeroException {
    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    final Future<Result> future = executorService
      .schedule(() -> expression.evaluate(), delay.toMillis(), TimeUnit.MILLISECONDS);

    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw ExceptionUtils.throwAsUncheckedException(e);
    } finally {
      executorService.shutdownNow();
    }
  }

  @Override
  public String toString() {
    // FIXME (jaeeun) 2020-12-16: delay format
    return format("%s", expression.toString());
  }
}
