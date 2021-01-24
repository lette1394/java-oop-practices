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

    // 여기서 가짜 File을 만들어서 리턴한다고 해도 문제
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