package operation

import persistence.infrastructure.http.HttpStorage
import spock.lang.Specification

class HttpStorageTest extends Specification {

  def 'test1'() {
    def storage = new HttpStorage()

    storage.remove("id")
  }

  // 1. local 서버를 띄워서 테스트한다
  // : 테스트 하는 목록
  //  - java.net.http.HttpClient
  //  - request logic
  //  - translate logic
  //
  // 2. java.net.http.HttpClient에 mock을 적용한다
  // : 테스트 하는 목록
  //  - java.net.http.HttpClient
  //  - request logic
  //  - translate logic
  //
  // 3. request / translate 만
  // : 테스트 하는 목록
  //  - java.net.http.HttpClient
  //  - request logic
  //  - translate logic
  //
  // 테스트: 우리가 작성한 부분에 대해서만
  //  mock: 우리가 작성한 거에 대해서만!
  //  test도 코드이므로 아키텍처 경계를 지켜야한다
  //
  //
}
