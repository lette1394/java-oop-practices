package com.github.lette1394.solid.X_5_SOLID_LSP_1;

import java.io.File;
import java.nio.file.Path;

public class DatabaseStore implements Store {
  public String readAllText(Path path) {
    // read from database;
    return "";
  }

  public void writeAllText(Path path, String string) {
    // write to database;
  }

  public File getFile(long id, String workingDirectory) {
    // LSP 위반!
    throw new UnsupportedOperationException();
    // 진짜 문제: file system을 사용하지 않으면서 File info를 어떻게 전달해줄거냐?
    // 일단 어렵고 모르겠으니까 UOE를 던지는거다.

    // 이 메서드는 MessageStore에서 사용되므로, UOE를 던지면 시스템은 동작하지 않을것이다.
    // fake file info?

    // 여기서 가짜 File을 만들어서 리턴한다고 해도 문제
    // 구현을 맘대로? => 문제를 더 복잡하게 키우는 꼴
    // 구현을 제대로? => 서로 개념이 다른데 가능한가?
    //
    // File 개념과 무관한, 그러니까 구현 세부사항과 무관한
    // 공통의 정보를 담는 무언가가 필요함
    //
    // 여기서는 exist()로 존재 여부를 따지기 때문에
    // Info 클래스를 만들고 그 안에 exist() 를 구현하고 있는...
    //
    // 그런데 그럴꺼면 차라리 그냥 Store interface 에다가
    // isExist()를 만들고말지.
  }
}