package remover

import spock.lang.Specification

import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.CompletionStage

class RetryableAsyncRemoveOperationTest3 extends Specification {
  Storage storage
  String unknownId
  AsyncRemoveOperation alwaysFailedOperation

  def setup() {
    storage = new MemoryStorage()
    unknownId = anyNonBlankString()
    alwaysFailedOperation = Mock()
  }

  def 'retry 4: refactor test assertions'() {
    given: 'retry count == 3'
      var retryCount = 3
      var subject = subjectWith(retryCount)
    when:
      await subject.remove(unknownId)
    then:
      thrown CompletionException
      retryCount * alwaysFailedOperation.remove(unknownId) >> failed()
  }

  def '''
      ## retry 5:

      the other way (with)

      '''() {
    given:
      var retryCount = 3
      var subject = subjectWith(retryCount)
    when:
      await subject.remove(unknownId)
    then:
      thrown CompletionException
      with(alwaysFailedOperation) {
        retryCount * remove(unknownId) >> failed()
      }
  }

  private static CompletableFuture<Object> failed() {
    return CompletableFuture.failedFuture(new RuntimeException())
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
