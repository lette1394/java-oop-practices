package com.github.lette1394.solid.X_5_SOLID_LSP_1;

import com.github.lette1394.solid.X_3_SOLID_SRP.Files;
import java.io.File;
import java.nio.file.Path;

public class FileStore implements Store {
  public String readAllText(Path path) {
    return Files.readAllBytes(path);
  }

  public void writeAllText(Path path, String string) {
    Files.write(path, string);
  }

  public File getFile(long id, String workingDirectory) {
    // 왜 workingDirectory를 parameter로 받아야 하나?
    return Path.of(workingDirectory, id + ".txt").toFile();
  }
}

// field vs parameter
// 언제 class field로 받고
// 언제 parameter로 받아야 하는가?
//
// 모든 구현을 parameter로만 받을 수 있음
// 마찬가지로 모든 구현을 field로만 만들 수 있음
//
// 그러나 모든 구현을 parameter로 하면 단순히 delegate, 즉 전달만 하는 애들이 많아지고
// 반대로 field로 다 구현하면 인스턴스 생성이 매우 귀찮고 까다로워짐
// 따라서 균형이 중요한데, 기준이 있음
//
// ==> 정보의 "생명주기"
// (음... 논리가 좀 빈약한거같으니 좀 더 생각해보자)
//
//
//
//
//
//
//
//
// 인터페이스에서는 당연히 그 인터페이스의 추상화 수준에 맞는
// parameter로 되어야 함
