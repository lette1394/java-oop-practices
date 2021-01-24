package com.github.lette1394.solid.X_5_SOLID_LSP_1;

import java.io.File;
import java.nio.file.Path;

public interface Store {
  String readAllText(Path path);
  // parameter: Path
  // 이건 추상화 레벨에 맞지 않다.
  // 추상화가 되어있지 않고 FileSystem 을 염두에 둔 개념이다
  // 따라서 여기서의 추상화 레벨과 일치하는 무언가로 대체가 되어야 한다.
  // ==> ID.
  //
  // String path도 마찬가지다. type이 Path가 아닐 뿐이지
  // "암시적"으로 file system path을 가리키고 있기 때문에
  // 결과적으로는 Path path와 다를게 없다.
  // 오히려 명시적으로 드러내는게 아니므로 더 나쁘다.


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


