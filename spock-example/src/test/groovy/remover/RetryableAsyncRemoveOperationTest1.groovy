package remover

import spock.lang.Specification

import java.util.concurrent.CompletionException
import java.util.concurrent.CompletionStage

class RetryableAsyncRemoveOperationTest1 extends Specification {
  Storage storage
  String unknownId

  def setup() {
    storage = new MemoryStorage()
    unknownId = anyNonBlankString()
  }

  def 'retry 1: naive test'() {
    given:
      var subject = subjectWithRetryCount(3)
    when:
      await subject.remove(unknownId)
    then:
      def e = thrown(CompletionException)
      def cause = e.cause

      cause.class == CannotRemoveException
      cause.message == "retry count exceed: 3"
      cause.suppressed.size() == 3
  }

  private RetryableAsyncRemoveOperation subjectWithRetryCount(int retryCount) {
    return new RetryableAsyncRemoveOperation(new HttpRemoveOperation(storage), retryCount)
  }

  private static await(CompletionStage<?> stage) {
    return stage.toCompletableFuture().join()
  }

  private static String anyNonBlankString() {
    return "<any-string>" // may be random string
  }
}
