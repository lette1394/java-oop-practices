package operation.application;

import static java.util.concurrent.CompletableFuture.failedFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import operation.domain.AsyncRemoveOperation;
import operation.domain.CannotRemoveException;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class RetryableAsyncRemoveOperation implements AsyncRemoveOperation {
  private final AsyncRemoveOperation operation;
  private final int maxRetryCount;

  @Override
  public CompletionStage<Void> remove(String id) {
    return remove(id, 0, new ArrayList<>());
  }

  public CompletionStage<Void> remove(String id, int retryCount, List<Throwable> suppressed) {
    if (retryCount < maxRetryCount) {
      return exceptionallyCompose(operation.remove(id), e -> {
        suppressed.add(e);
        return remove(id, retryCount + 1, suppressed);
      });
    }

    return failedFuture(exception(retryCount, suppressed));
  }

  private static <T> CompletionStage<T> exceptionallyCompose(
    CompletionStage<T> stage,
    Function<Throwable, ? extends CompletionStage<T>> fn) {
    return dereference(wrap(stage).exceptionally(fn));
  }

  private static <T> CompletionStage<T> dereference(
    CompletionStage<? extends CompletionStage<T>> stage) {
    return stage.thenCompose(Function.identity());
  }

  private static <T> CompletionStage<CompletionStage<T>> wrap(CompletionStage<T> stage) {
    return stage.thenApply(CompletableFuture::completedFuture);
  }

  @NotNull
  private CannotRemoveException exception(int retryCount, List<Throwable> suppressedThrowableList) {
    final var exception = new CannotRemoveException("retry count exceed: " + retryCount);
    for (Throwable throwable : suppressedThrowableList) {
      exception.addSuppressed(throwable);
    }
    return exception;
  }
}
