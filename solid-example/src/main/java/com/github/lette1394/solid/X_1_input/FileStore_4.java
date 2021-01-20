package com.github.lette1394.solid.X_1_input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStore_4 {
  private String workingDirectory;

  public FileStore_4(String workingDirectory) {
    this.workingDirectory = workingDirectory;
  }

  public void save(long id, String message) throws IOException {
    final Path path = getFilePath(id);
    Files.write(path, message.getBytes());
  }

  public String read(long id) throws IOException {
    final Path path = getFilePath(id);
    final byte[] bytes = Files.readAllBytes(path);
    return new String(bytes);
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }
}

// 생성자가 추가되었다. 이걸로 괜찮은가?
// 앞서 "발견"한 불변식을 깨트릴 수 있는 방법은 없는가?
//  ==> 있다. 2가지나.
//  1. new FileStore_Input_4(null) => check precondition guard clause
//  2. public setter => private setter로 바꾸자