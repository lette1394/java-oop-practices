package com.github.lette1394.solid.X_2_output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_1 {
  private String workingDirectory;

  public FileStore_1(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException();
    }

    // LIKE DOCUMENTATION ! for programmer
    // - 어떠한 상황인가?
    // - 문제가 무엇인가?
    // - 원인이 무엇인가?
    // - 어떻게 해결하는가?
    // - 유머?
    if (!Files.exists(Path.of(workingDirectory))) {
      throw new IllegalArgumentException(String.format(
        "You tried to provide a working directory which does not exist."
          + "The FileStore class prevented to work in illegal state, "
          + "so you have to check that a valid working directory exists in your filesystem. "
          + "This exception was created from statically typed pre-condition."
          + "workingDirectory: [%s]", workingDirectory));
    }
    this.workingDirectory = workingDirectory;
  }

  public void save(long id, String message) throws IOException {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    Files.write(path, message.getBytes());
  }


  public String read(long id) throws IOException {
    final Path path = Path.of(this.workingDirectory, id + ".txt");

    // 파일이 존재하지 않을 때 exception을 던질 거다.
    // read()가 성공하면 항상 return valid string
    final byte[] bytes = Files.readAllBytes(path);


    return new String(bytes);
  }

  public String getFilePath(long id) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    // 항상 string 을 반환하는가? null을 반환하지는 않나?
    // 이게 실제로 존재하는지 신경쓰는가?
    // workingDirectory => null일 수 없다
    // id => null일 수 없다
    // ".txt" => null일 수 없다
    // 전체 => null일 수 없다
    return path.toString();
  }
}
