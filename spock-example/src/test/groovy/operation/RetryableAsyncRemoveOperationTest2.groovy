package operation

import operation.application.RetryableAsyncRemoveOperation
import operation.domain.AsyncRemoveOperation
import operation.domain.CannotRemoveException
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
import spock.lang.Specification

import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.CompletionStage

class RetryableAsyncRemoveOperationTest2 extends Specification {
  Storage storage
  String unknownId

  var alwaysFailedOperation = Mock(AsyncRemoveOperation)

  def setup() {
    storage = new MemoryStorage()
    unknownId = anyNonBlankString()
  }

  def 'retry 2: verify interactions'() {
    given:
      var subject = subjectWithRetryCount(3)
    when:
      await subject.remove(unknownId)
    then:
      def e = thrown(CompletionException)
      def cause = e.cause

      3 * alwaysFailedOperation.remove(unknownId) >> CompletableFuture.failedFuture(new RuntimeException())

      cause.class == CannotRemoveException
      cause.message == "retry count exceed: 3"
      cause.suppressed.size() == 3
  }

  def 'retry 3: refactor test assertions'() {
    given:
      var retryCount = 3
      var subject = subjectWith(retryCount)
    when:
      await subject.remove(unknownId)
    then:
      thrown CompletionException
      retryCount * alwaysFailedOperation.remove(unknownId) >> failed()
  }

  private static CompletableFuture<Object> failed() {
    return CompletableFuture.failedFuture(new RuntimeException())
  }

  private RetryableAsyncRemoveOperation subjectWithRetryCount(int retryCount) {
    return new RetryableAsyncRemoveOperation(alwaysFailedOperation, retryCount)
  }

  private RetryableAsyncRemoveOperation subjectWith(int retryCount) {
    return new RetryableAsyncRemoveOperation(alwaysFailedOperation, retryCount)
  }

  private static await(CompletionStage<?> stage) {
    return stage.toCompletableFuture().join()
  }

  private static String anyNonBlankString() {
    return "<any-string>" // may be random string
  }
}
