package com.github.lette1394.calculator2.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Test;

public class CastingTest {

  @Test
  void integer() {
    assertThat(50 + 100, is(150));
    assertThat(50 + 100L, is(150L));
    assertThat(50L + 100, is(150L));
    assertThat(50L + 100L, is(150L));

    assertThat(Integer.MAX_VALUE + 1, is(-2147483648));
    assertThat((long) Integer.MAX_VALUE + 1, is(2147483648L));
    assertThat(Integer.MAX_VALUE + 1L, is(2147483648L));
    assertThat(Integer.MAX_VALUE + (long) 1, is(2147483648L));
  }

  @Test
  void decimal() {
    assertThat(50F + 100F, is(150F));
    assertThat(50F + 100D, is(150D));
    assertThat(50D + 100F, is(150D));
    assertThat(50D + 100D, is(150D));

    assertThat(Float.MAX_VALUE, is(3.4028235E38F));
    assertThat(Float.toString(Float.MAX_VALUE), is("3.4028235E38"));
    assertThat(Float.toString(12345.12345F), is("12345.123"));
    assertThat(String.format("%.10f", 12345.12345F), is("12345.1230468750"));
    assertThat(String.format("%.10f", 12345.12345D), is("12345.1234500000"));

    assertThat(new DecimalFormat(".#####").format(12345.12345D), is("12345.12345"));
    assertThat(new DecimalFormat(".#####").format(12345.12345f), is("12345.12305"));
    assertThat(new DecimalFormat(".0000000").format(12345.12345D), is("12345.1234500"));
    assertThat(new DecimalFormat(".0000000").format(12345.12345f), is("12345.1230469"));

    final DecimalFormat formatter1 = new DecimalFormat(".#");
    formatter1.setRoundingMode(RoundingMode.DOWN);
    assertThat(formatter1.format(6.59F), is("6.5"));

    final DecimalFormat formatter2 = new DecimalFormat(".#");
    formatter2.setRoundingMode(RoundingMode.UP);
    assertThat(formatter2.format(6.51F), is("6.6"));

    assertThat(Float.MAX_VALUE, is(Float.MAX_VALUE));
    assertThat(Float.MAX_VALUE + 1, is(Float.MAX_VALUE));
    assertThat(Float.MAX_VALUE + 12, is(Float.MAX_VALUE));
    assertThat(Float.MAX_VALUE * Float.MAX_VALUE, is(Float.POSITIVE_INFINITY));
    assertThat(Float.MIN_NORMAL * Float.MIN_VALUE, is(0F));
    assertThat(Double.isFinite((double) Float.MAX_VALUE * Float.MAX_VALUE), is(true));
    assertThat(Double.MAX_VALUE * Double.MAX_VALUE, is(Double.POSITIVE_INFINITY));

    assertThat(Double.MAX_VALUE + 12, is(Double.MAX_VALUE));
    assertThat(Double.MAX_VALUE + 99999999999L, is(Double.MAX_VALUE));
    assertThat(Double.MAX_VALUE + 99999999999D, is(Double.MAX_VALUE));
  }
}
