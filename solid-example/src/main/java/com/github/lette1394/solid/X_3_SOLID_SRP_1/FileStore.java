package com.github.lette1394.solid.X_3_SOLID_SRP_1;

import com.github.lette1394.solid.X_3_SOLID_SRP.Files;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileStore {
  @Getter
  private final String workingDirectory;
  private final Map<Long, String> cache;
  private final StoreLogger log;

  public FileStore(String workingDirectory) {
    if (workingDirectory == null) {
      throw new IllegalArgumentException("workingDirectory is null");
    }
    if (!Path.of(workingDirectory).toFile().exists()) {
      throw new IllegalArgumentException("workingDirectory not exist");
    }
    this.workingDirectory = workingDirectory;
    this.cache = new HashMap<>();
    this.log = new StoreLogger();
  }

  public void save(long id, String message) {
    log.saving(id);
    File file = getFile(id);
    Files.write(file.toPath(), message);
    cache.put(id, message);
    log.saved(id);
  }

  public Optional<String> read(long id) {
    log.reading(id);
    File file = getFile(id);
    if (!file.exists()) {
      log.didNotFound(id);
      return Optional.empty();
    }
    String message = cache.computeIfAbsent(id,
      __ -> Files.readAllBytes(file.toPath()));
    log.returning(id);
    return Optional.of(message);
  }

  public File getFile(long id) {
    return Path.of(workingDirectory, id + ".txt").toFile();
  }
}

// SOLID를 하나씩 분리할 수 없지만 여기서는 해보도록 한다


// SRP: 하나의 클래스는 오직 하나의 책임을 가져야 한다.
// 여기서 책임 이라는 말은 무엇인가?
// a class has only one reason to change: separation of concerns
// concern을 분리해서 접근하자
//
// Do one thing, and do it well:
// sample: unix system: command line compose

//////////////////////////
// 여기서 질문.
// 이 클래스는 변경 해야할 이유가 몇 가지나 있습니까? 책임을 몇 가지나 가지고 있습니까?
// '변경' 이라는 거는 여기 적힌 코드를 한 글자라도 바꾸고자 할 때를 말합니다.
// 1. logging
// 2. caching
// 3. storage (db에서 읽고 쓰고 싶으면?)
// 4. orchestration
//    => read 를 예시로 들으면,
//    작업이 진행되는 순서를 '어디선가'는 관리해야한다.
//    뭐 예를 들어서 log를 남기고 나서 file을 열고, 그 다음에 캐시에 넣고...
//    이런 동작의 순서를 관리하는 놈이 하나가 필요하다. (sequence!)
//    (특정 순서로 실행되어야하고 특정 조건에서는 이게 돌아야 하고 저게 돌아야 하고...)
//
// 여기서 우리가 SRP를 적용하면, 각각의 책임을 별도의 클래스로 뽑아야 한다.
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//






























