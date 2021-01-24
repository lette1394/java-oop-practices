package com.github.lette1394.solid.X_6_SOLID_ISP_3;

import java.util.Optional;

public interface Store {
  Optional<String> readAllText(long id);

  // 이 메서드가 뽑힐때를 생각해보면
  // 그냥 header inteface 였다.
  // 다른 이름으로 변경되어도 아무런 문제가 없는 것.
  //
  //
  void writeAllText(long id, String string);
}


