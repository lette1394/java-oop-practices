package com.github.lette1394.solid.X_2_output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_2_Tester_Doer {
  private String workingDirectory;

  public static void main(String[] args) throws IOException {
    final FileStore_2_Tester_Doer fileStore = new FileStore_2_Tester_Doer();

    // main-problem: not thread-safe
    String message = "";
    if (fileStore.exists(13)) {
      message = fileStore.read(13);
    }
  }

  public boolean exists(long id) {
    return Files.exists(getFilePath(id));
  }

  public String read(long id) throws IOException {
    final Path path = getFilePath(id);

    // 파일이 존재하지 않을 때 exception을 던질 거다.
    // read()가 성공하면 항상 return valid string
    final byte[] bytes = Files.readAllBytes(path);
    return new String(bytes);
  }

  // 매우 복잡하고
  // 에러를 구분해야하면 여러 에러코드/에러 계층이 발생할 수 있다.
  public String read1(long id) throws IOException {
    final Path path = getFilePath(id);
    if (Files.exists(path)) {
      final byte[] bytes = Files.readAllBytes(path);
      return new String(bytes);
    }
    throw new IllegalArgumentException("not found id");
  }

  public String read2(long id) {
    final Path path = getFilePath(id);

    try {
      final byte[] bytes = Files.readAllBytes(path);
      return new String(bytes);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }
}
