package operation

import operation.domain.AsyncRemoveOperation
import operation.infrastructure.StorageRemoveOperation
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
import spock.lang.Specification

class RemoverTest3 extends Specification {
  Storage storage
  String savedId

  def setup() {
    storage = new MemoryStorage()
    savedId = "id-123"
//  existingId
//  remainingId
//  validId
  }

  def 'remove 6: Add meaning to id variable'() {
    given:
      storage.save(savedId, "message")
      AsyncRemoveOperation operation = new StorageRemoveOperation(storage)
    when:
      operation.remove(savedId).toCompletableFuture().join()
    then:
      doesRemoved(savedId)
  }

  private boolean doesRemoved(String id) {
    return !storage.exists(id).join()
  }
}
