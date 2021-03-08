package integration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Server {
  private final String accessControllingHttpEndpoint;

  public void run() {

  }

  public void shutdown() {

  }

  public void updateAccessControlEndpoint(String accessControllingHttpEndpoint) {

  }

  public String getEndpoint() {
    return "http://localhost:8081";
  }
}
