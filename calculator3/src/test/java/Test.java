import java.util.concurrent.CompletableFuture;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;

public class Test {

  static {
    CompletableFuture.runAsync(() -> {}, command -> {
      command.run();
    });
  }
  Matcher<String> matcher() {
    return new TypeSafeDiagnosingMatcher<>() {
      @Override
      protected boolean matchesSafely(String item, Description mismatchDescription) {
        return false;
      }

      @Override
      public void describeTo(Description description) {

      }
    };
  }

  Matcher<String> matcher2() {
    return new TypeSafeMatcher<>() {
      @Override
      protected boolean matchesSafely(String item) {
        return false;
      }

      @Override
      public void describeTo(Description description) {

      }

      @Override
      protected void describeMismatchSafely(String item, Description mismatchDescription) {

      }
    };
  }

  Matcher<String> matcher3() {
    return new CustomTypeSafeMatcher<>("wow") {
      @Override
      protected boolean matchesSafely(String item) {
        return false;
      }
    };
  }
}
