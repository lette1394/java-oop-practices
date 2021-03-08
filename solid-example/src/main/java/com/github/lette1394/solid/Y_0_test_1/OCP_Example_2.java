package com.github.lette1394.solid.Y_0_test_1;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

interface Phone extends Comparable<Phone> {
  void dial();
}

public class OCP_Example_2 {
  public static void main(String[] args) {
    final List<Phone> phones = Lists.newArrayList(
      new iPhone(4),
      new Galaxy(1),
      new Galaxy(2),
      new OnePlus(5),
      new Pixel(6),
      new Galaxy(3),
      new iPhone(7),
      new OnePlus(8)
    );

    final Phone phone = new OrderedCompositePhone(phones);
    phone.dial();
  }
}

@RequiredArgsConstructor
class iPhone implements Phone {
  private final int id;

  @Override
  public int compareTo(Phone o) {
    if (o instanceof Galaxy) {
      return 1;
    }
    return 0;
  }

  @Override
  public void dial() {
    System.out.println(id + "-iphone");
  }
}

@RequiredArgsConstructor
class Galaxy implements Phone {
  private final int id;

  @Override
  public int compareTo(Phone o) {
    if (o instanceof Galaxy) {
      return 0;
    }
    return -1;
  }

  @Override
  public void dial() {
    System.out.println(id + "-galaxy");
  }
}

@RequiredArgsConstructor
class Pixel implements Phone {
  private final int id;

  @Override
  public int compareTo(Phone o) {
    if (o instanceof Galaxy) {
      return 1;
    }
    return 0;
  }

  @Override
  public void dial() {
    System.out.println(id + "-pixel");
  }
}

@RequiredArgsConstructor
class OnePlus implements Phone {
  private final int id;

  @Override
  public int compareTo(Phone o) {
    if (o instanceof Galaxy) {
      return 1;
    }
    return 0;
  }

  @Override
  public void dial() {
    System.out.println(id + "-onePlus");
  }
}

@RequiredArgsConstructor
class OrderedCompositePhone implements Phone {
  private final List<Phone> phones;

  @Override
  public void dial() {
    Collections.sort(phones);
    phones.forEach(Phone::dial);
  }

  @Override
  public int compareTo(Phone o) {
    throw new UnsupportedOperationException();
  }
}










class MyImplements implements ExtractedInterface {
  @Override
  public void doA() {
  }

  @Override
  public String getB() {
    return "";
  }

  @Override
  public List<Integer> parseC() {
    return null;
  }
}
















