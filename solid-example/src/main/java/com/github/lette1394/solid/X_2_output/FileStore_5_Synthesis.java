package com.github.lette1394.solid.X_2_output;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileStore_5_Synthesis {
  private String workingDirectory;

  public static void main(String[] args) {
    final FileStore_5_Synthesis fileStore = new FileStore_5_Synthesis();

    // fluent API 장점: 버그가 끼어들 틈이 없다! - 앞서 봤던 다른거랑 비교해보자
    // type-safe, read() 메서드가 더더더더 많은 정보를 준다!
    final String message = fileStore.read(13).orElse("");
  }


  // vs public String read(long id)
  // caller에게 더 많은 guarantee 를 줄 수록 caller, 즉 개발자는 이해하기도 쉽고 사용하기도 쉽다
  //
  // 이제 Optional<String>read() 메서드는 절대로
  // null Optional 객체를 반환하지 않는다는 사실을 우리는 모두 알고 있다.
  // 자연스럽게 읽힌다!
  public Optional<String> read(long id) {
    final Path path = getFilePath(id);

    try {
      final byte[] bytes = Files.readAllBytes(path);
      return Optional.of(new String(bytes));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public Path getFilePath(long id) {
    return Path.of(this.workingDirectory, id + ".txt");
  }

  public String getFileName(long id) {
    return getFilePath(id).toString();
  }
}


// 여태까지 봤던게 다 캡슐화에 대한 거였다.
// 생각보다 별게 아니다.
// 팀원을 위해, 나를 위해, 그리고 6개월 후의 나를 위해
// 우리가 작성하는 클래스를 사용하기/이해하기 쉽게 만드는게 전부다.


// 뭔가 잘못된 코드다 => 뭔가 이해하기 어렵다.
// 이 "뭔가"를 구체화하는 게 중요하다.
// 구체적인 ****기준****이 중요하다.

// (FileStore 첫 번째 버전과 마지막 버전의 public method signature 비교)
// Optional<String> read(long id)
// public String getFileName(long id)
// Optional<String> 과 String는 서로 다르다. 말하고 있는게 서로 다름 guarantee!

// Reading code에 시간을 덜 쏟으면, Writing code에 시간을 더 쓸 수 있다.
// - Make code easier to read
// - Make code easier to reason about

// how do we? ==> DE-COMPOSED !!!
// adhere CQS

// encapsulation is a balance
// - public static void main(String[] args) <- 엄청난 캡슐화!! 그러나 유용성은...?
// - everything public ? <- 매우 낮은 캡슐화... 그러나 유용성은!!
// 기준은? invariant!!
// invariant가 깨지지만 않으면 public으로 공개해도 "괜찮다"
// 여기서의 원칙은 "write for STUPID programmer"
//
// public API alone -> 이해하기 매우 쉽다 (Functional Interface)
//
// Make invalid state be impossible
// Never return null
//
//
//
// 다시 postel's law / CQS 학습
//
//









