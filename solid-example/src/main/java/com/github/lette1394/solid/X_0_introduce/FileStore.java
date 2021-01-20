package com.github.lette1394.solid.X_0_introduce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStore {
  private String workingDirectory;
  private EventHandler messageRead;

  // ## 1
  // 리턴값 String은 뭘 뜻하는가?
  // 문서가 없다고 가정해보자
  // 대부분 사람들은 구현을 본다.
  public String save(long id, String message) throws IOException {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    Files.write(path, message.getBytes());
    return path.toString();
  }

  // ## 2
  // read() 메서드는 어떻지?
  //
  // 이것도 마찬가지로. ****구현****을 읽지 않으면 이해하기 어렵다
  //
  public void read(long id) throws IOException {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    final byte[] bytes = Files.readAllBytes(path);
    messageRead.messageRead(new EventArgs(new String(bytes)));
  }

  interface EventHandler {
    void messageRead(EventArgs eventArgs);
  }

  static class EventArgs {
    private String message;

    public EventArgs(String message) {
      this.message = message;
    }
  }

}
