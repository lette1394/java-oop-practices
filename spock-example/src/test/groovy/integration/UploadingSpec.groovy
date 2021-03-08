package integration

import spock.lang.Specification

class UploadingSpec extends Specification {
  def storage = new Storage()
  def server = new Server()

  void setup() {
    server.run()
  }

  void cleanup() {
    server.shutdown()
  }

  void 'access controlled uploading'() {
    given: 'an access controlling server'
      def accessServer = new AlwaysAllowingAccessControllingServer().run()
    and: 'its HTTP endpoint'
      server.updateAccessControlEndpoint(accessServer.getEndpoint())

    when: 'client request upload'
      def client = new Client(server.getEndpoint())
      client.upload('<the payload>')

    then: 'the payload will be uploaded successfully'
      storage.get() === '<the payload>'
  }
}
