package com.github.lette1394.calculator2.expression;

import static com.github.lette1394.calculator2.common.ExceptionUtils.throwAsUncheckedException;
import static java.lang.String.format;

import com.github.lette1394.calculator2.operator.Operator;
import com.github.lette1394.calculator2.result.Result;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PreemptiveTimeoutOperator implements Operator {
  private final Operator operator;
  private final Duration duration;

  public PreemptiveTimeoutOperator(Operator operator, Duration duration) {
    this.operator = operator;
    this.duration = duration;
  }

  private Callable<Result> getResultCallable(Expression left, Expression right) {
    return new Callable<>() {
      @Override
      public Result call() throws Exception {
        return operator.apply(left, right).evaluate();
      }

      @Override
      public String toString() {
        return format("future expression: [%s], timeout: [%s]", operator.toString(),
          duration.toString());
      }
    };
  }

  @Override
  public String toString() {
    return format("expression: [%s], timeout: [%s]", operator.toString(), duration.toString());
  }

  @Override
  public Expression apply(Expression left, Expression right) throws EvaluationTimeoutException {
    // FIXME (jaeeun) 2020-12-16: 외부에서 주입?이 필요할까?
    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    final Future<Result> future = executorService.submit(getResultCallable(left, right));

    return new Expression() {
      @Override
      public Result evaluate() throws DivideByZeroException {
        try {
          return future.get(duration.toMillis(), TimeUnit.MILLISECONDS);
          // FIXME (jaeeun) 2020-12-16: exception translator
        } catch (TimeoutException e) {
          throw new EvaluationTimeoutException(e, String.format("%s %s %s", left, operator, right));
        } catch (ExecutionException e) {
          if (e.getCause() instanceof EvaluationTimeoutException) {
            throw new EvaluationTimeoutException(e.getCause(),
              ((EvaluationTimeoutException) e.getCause()).getExpression());
//            throw new EvaluationTimeoutException(e.getCause(), String.format("%s %s %s", left, operator, right));
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
        return String.format("%s %s %s", left, operator, right);
      }
    };
  }
}
