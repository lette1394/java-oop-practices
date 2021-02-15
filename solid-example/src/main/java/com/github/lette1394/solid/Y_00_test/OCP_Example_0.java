package com.github.lette1394.solid.Y_00_test;

import java.util.List;
import lombok.RequiredArgsConstructor;

public class OCP_Example_0 {
  public static void main(String[] args) {
    final List<Object> objects = List.of(
      new Iphone(1),
      new Galaxy(2),
      new Galaxy(3),
      new Galaxy(4),
      new Iphone(5));

    for (Object object : objects) {
      if (object instanceof Iphone) {
        ((Iphone) object).dialIphone();
        continue;
      }
      if (object instanceof Galaxy) {
        ((Galaxy) object).dialGalaxy();
        continue;
      }
      throw new IllegalArgumentException();
    }
  }
}





@RequiredArgsConstructor
class Iphone {
  private final int id;

  public void dialIphone() {
    System.out.println(id + "-iphone");
  }
}

@RequiredArgsConstructor
class Galaxy {
  private final int id;

  public void dialGalaxy() {
    System.out.println(id + "-galaxy");
  }
}
