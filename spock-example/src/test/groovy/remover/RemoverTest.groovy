package remover

import org.junit.jupiter.api.Assertions
import spock.lang.Specification

class RemoverTest extends Specification {

  def 'remove 1: Initial code'() {
    String id = "id-123"
    Storage storage = new Storage()
    storage.save(id, "message")
    AsyncRemoveOperation operation = new HttpRemoveOperation(storage)
    operation.remove(id).toCompletableFuture().join()

    expect:
      Assertions.assertFalse(storage.exists(id).join())
  }

  def 'remove 2: Add BDD labels'() {
    given:
      String id = "id-123"
      Storage storage = new Storage()
      storage.save(id, "message")
      AsyncRemoveOperation operation = new HttpRemoveOperation(storage)
    when:
      operation.remove(id).toCompletableFuture().join()
    then:
      Assertions.assertFalse(storage.exists(id).join())
  }

  def 'remove 3: Make assertion be readable'() {
    given:
      String id = "id-123"
      Storage storage = new Storage()
      storage.save(id, "message")
      AsyncRemoveOperation operation = new HttpRemoveOperation(storage)
    when:
      operation.remove(id).toCompletableFuture().join()
    then:
      isNotExist(id, storage)
  }


  def 'remove 4: Make assertion be more readable'() {
    given:
      String id = "id-123"
      Storage storage = new Storage()
      storage.save(id, "message")
      AsyncRemoveOperation operation = new HttpRemoveOperation(storage)
    when:
      operation.remove(id).toCompletableFuture().join()
    then:
      isRemoved(id, storage)
  }

  private static boolean isRemoved(String id, Storage storage) {
    return !storage.exists(id).join()
  }

  private static boolean isNotExist(String id, Storage storage) {
    return !storage.exists(id).join()
  }
}
