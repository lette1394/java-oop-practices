package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.common.ExceptionUtils.throwAsUncheckedException;
import static java.lang.String.format;

import com.github.lette1394.calculator2.result.Result;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PreemptiveTimeoutExpression implements Expression {
  private final Expression expression;
  private final Duration duration;

  public PreemptiveTimeoutExpression(Expression expression, Duration duration) {
    this.expression = expression;
    this.duration = duration;
  }

  @Override
  public Result evaluate() throws DivideByZeroException,
                                  OverflowException,
                                  UnderflowException,
                                  EvaluationTimeoutException {
    // FIXME (jaeeun) 2020-12-16: thread pool 매번 생성하는거 overhead는 없을까?
    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    final Future<Result> future = executorService.submit(callableResult());

    try {
      return future.get(duration.toMillis(), TimeUnit.MILLISECONDS);
      // FIXME (jaeeun) 2020-12-16: exception translator
    } catch (TimeoutException e) {
      throw new EvaluationTimeoutException(e, String.format("%s", expression));
    } catch (ExecutionException e) {
      if (e.getCause() instanceof EvaluationTimeoutException) {
        throw new EvaluationTimeoutException(e.getCause(),
          ((EvaluationTimeoutException) e.getCause()).getExpression());
      }
      // FIXME (jaeeun) 2020-12-16: naive implementation
      throw throwAsUncheckedException(e.getCause());
    } catch (InterruptedException e) {
      throw new UnrecoverableException(e);
    } finally {
      executorService.shutdownNow();
    }
  }

  @Override
  public String toString() {
    // FIXME (jaeeun) 2020-12-16: timeout난 그 부분만 출력해 줄 수는 없나?
    return String.format("%s", expression);
  }

  private Callable<Result> callableResult() {
    return new Callable<>() {
      @Override
      public Result call() throws Exception {
        return expression.evaluate();
      }

      @Override
      public String toString() {
        // FIXME (jaeeun) 2020-12-16: toString convention
        return format("future expression: [%s], timeout: [%s]", expression, duration.toString());
      }
    };
  }
}
