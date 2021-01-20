package com.github.lette1394.solid.X_1_input;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class FileStore_Input {
  private String workingDirectory;

  @SneakyThrows
  public void save(long id, String message) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    Files.write(path, message.getBytes());
  }

  @SneakyThrows
  public String read(long id) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    final byte[] bytes = Files.readAllBytes(path);
    return new String(bytes);
  }

  public String getFilePath(long id) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    return path.toString();
  }
}


// 이 구현 중에서 어떤 input이 주어졌을 때
// 뭔가 잘못될 부분이 있는가?
// 후보1: id < 0 - 동작함
// 후보2: message == null - 역시 동작함
// 후보3: workingDirectory- 여기는 좀 문제.
//    - null 이면? exception or 잘못된 동
//    - 잘못된 directory이면? 모든 메서드가 영향 받음
//    -