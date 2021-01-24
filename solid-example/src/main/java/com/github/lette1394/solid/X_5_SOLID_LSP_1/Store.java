package com.github.lette1394.solid.X_5_SOLID_LSP_1;

import java.io.File;
import java.nio.file.Path;

public interface Store {
  String readAllText(Path path);

  void writeAllText(Path path, String string);

  File getFile(long id, String workingDirectory);
}

// 전형적인 LSP 위반!!!
// => 구현 클래스에서 바로 interface를 추출한 경우
//
// 특별한 하나의 구현체를 위한 인터페이스임 (FileStore)
// 추상화를 잘못 한 경우.
//
//
// 그래서 어떤 interface에 많은 메서드가 있을 경우에
// LSP를 위반할 가능성이 높아진다.
//
// 여기서는 FileStore만 올바른 구현이다.
// 
//


