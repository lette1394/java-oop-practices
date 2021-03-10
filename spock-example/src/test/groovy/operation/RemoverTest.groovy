package operation

import operation.domain.AsyncRemoveOperation
import operation.infrastructure.StorageRemoveOperation
import org.junit.jupiter.api.Assertions
import persistence.domain.Storage
import persistence.infrastructure.MemoryStorage
import spock.lang.Specification

class RemoverTest extends Specification {

  def 'remove 1: Initial code'() {
    String id = "id-123"
    Storage storage = new MemoryStorage()
    storage.save(id, "message")
    AsyncRemoveOperation operation = new StorageRemoveOperation(storage)
    operation.remove(id).toCompletableFuture().join()

    expect:
      Assertions.assertFalse(storage.exists(id).join())
  }

  def 'remove 2: Add BDD labels'() {
    given:
      String id = "id-123"
      Storage storage = new MemoryStorage()
      storage.save(id, "message")
      AsyncRemoveOperation operation = new StorageRemoveOperation(storage)
    when:
      operation.remove(id).toCompletableFuture().join()
    then:
      Assertions.assertFalse(storage.exists(id).join())
  }

  def 'remove 3: Make assertion be readable'() {
    given:
      String id = "id-123"
      Storage storage = new MemoryStorage()
      storage.save(id, "message")
      AsyncRemoveOperation operation = new StorageRemoveOperation(storage)
    when:
      operation.remove(id).toCompletableFuture().join()
    then:
      doesNotExist(id, storage)
  }


  def 'remove 4: Make assertion be more readable'() {
    given:
      String id = "id-123"
      Storage storage = new MemoryStorage()
      storage.save(id, "message")
      AsyncRemoveOperation operation = new StorageRemoveOperation(storage)
    when:
      operation.remove(id).toCompletableFuture().join()
    then:
      doesRemoved(id, storage)
  }

  private static boolean doesRemoved(String id, Storage storage) {
    return !storage.exists(id).join()
  }

  private static boolean doesNotExist(String id, Storage storage) {
    return !storage.exists(id).join()
  }
}
