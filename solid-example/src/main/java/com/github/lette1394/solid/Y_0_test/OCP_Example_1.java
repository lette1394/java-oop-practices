package com.github.lette1394.solid.Y_0_test;

import com.google.common.collect.Lists;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;

public class OCP_Example_1 {
  public static void main(String[] args) {
    final Comparator<Phone> galaxyFirst = (o1, o2) -> {
      if (o1 instanceof Galaxy && o2 instanceof Galaxy) {
        return 0;
      }
      if (o1 instanceof Galaxy) {
        return -1;
      }
      return 1;
    };

    final List<Phone> phones = Lists.newArrayList(
      new iPhone(4),
      new Galaxy(1),
      new Galaxy(2),
      new OnePlus(7),
      new Pixel(5),
      new Galaxy(3),
      new iPhone(6),
      new OnePlus(8)
    );

    final Phone phone = new OrderedCompositePhone(phones, galaxyFirst);
    phone.dial();
  }
}

interface Phone {
  void dial();
}

@RequiredArgsConstructor
class iPhone implements Phone {
  private final int id;

  @Override
  public void dial() {
    System.out.println(id + "-iphone");
  }
}

@RequiredArgsConstructor
class Galaxy implements Phone {
  private final int id;

  @Override
  public void dial() {
    System.out.println(id + "-galaxy");
  }
}

@RequiredArgsConstructor
class Pixel implements Phone {
  private final int id;

  @Override
  public void dial() {
    System.out.println(id + "-pixel");
  }
}

@RequiredArgsConstructor
class OnePlus implements Phone {
  private final int id;

  @Override
  public void dial() {
    System.out.println(id + "-onePlus");
  }
}

@RequiredArgsConstructor
class OrderedCompositePhone implements Phone {
  private final List<Phone> phones;
  private final Comparator<Phone> comparator;

  @Override
  public void dial() {
    phones.sort(comparator);
    phones.forEach(Phone::dial);
  }
}