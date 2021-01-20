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

  // (이 문맥에서는) 우리가 관심있는 건, 어떤 id에 대한 String이 있냐 없냐지,
  // 어떤 다른게 아니라면...? 이걸 어떻게 구분하지?

  // return ""? (empty string) - 빈 메시지가 있으면 어쩌나?
  // return null?
  //   - getFilePath()은 항상 non-null 이고
  //   - read()은 어떨땐 null일 수 있다?
  //   => 어떨땐 이러고 저떨땐 저러면, API가 매우 혼란스러워지고 이러면 유저는 구현을 볼 수 밖에 없다.
  //   => 구현을 보면 생산성은 떨어지게 된다.
  //
  //   - @Nullable annotation을 붙이면 되지 않나?
  //   => 더 나쁘다. 거짓말을 하기 때문. 이 경우 annotation은 어떠한 강제성도 없기 때문에
  //   => @Nullable 인데 항상 non-null 인 경우가 발생할 것.
  //   => 결국 이 코드를 쓰는 모든 곳에서 null-check를 강요당하게 될거다.
  // how do we signal with this API, that read() method가 string을 리턴하는지 안하는지?
  // throw NotFoundException?
  public String read(long id) throws IOException {
    final Path path = Path.of(this.workingDirectory, id + ".txt");

    // 파일이 존재하지 않을 때 exception을 던질 거다.
    // read()가 성공하면 항상 return valid string
    final byte[] bytes = Files.readAllBytes(path);
    return new String(bytes);
  }

  // 매우 복잡하고
  // 에러를 구분해야하면 여러 에러코드/에러 계층이 발생할 수 있다.
  public String read1(long id) throws IOException {
    final Path path = Path.of(this.workingDirectory, id + ".txt");
    if (Files.exists(path)) {
      final byte[] bytes = Files.readAllBytes(path);
      return new String(bytes);
    }
    throw new IllegalArgumentException("not found id");
  }

  public String read2(long id) {
    final Path path = Path.of(this.workingDirectory, id + ".txt");

    try {
      final byte[] bytes = Files.readAllBytes(path);
      return new String(bytes);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
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
