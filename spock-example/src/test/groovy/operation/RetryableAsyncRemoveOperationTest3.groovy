package operation

import operation.application.RetryableAsyncRemoveOperation
import operation.domain.AsyncRemoveOperation
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
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

  def '''
    RetryableAsyncRemoveOperation 은

    ko: (O) 삭제에 실패하는 경우 정해진 횟수만큼 재시도 한 후에
            모두 실패하면 최종 실패 처리 된다

    ko: (X) 실패가 발생하면 retryCount 만큼 재시도를 하고,
            (retryCount+1) 번째 storage.remove(id)를 호출하기 전에 CannotRemoveException 예외를 던진다
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
