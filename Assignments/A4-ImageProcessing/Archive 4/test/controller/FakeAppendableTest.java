package controller;

import java.io.IOException;

/**
 * FakeAppendableTest class that implements Appendable to hardcode IOExceptions that can
 * be then passed to the controller and test the IllegalStateExceptions.
 */
public class FakeAppendableTest implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Appendable exception given character sequence");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Appendable exception given characters with index");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Appendable exception given with characters");
  }
}
