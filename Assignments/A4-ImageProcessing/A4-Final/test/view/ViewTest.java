package view;

import org.junit.Test;

import java.io.IOException;

import controller.FakeAppendableTest;
import model.ImageModel;
import model.ImageModelImpl;
import model.MockModel;

import static org.junit.Assert.assertEquals;

/**
 * tests for the view.
 */
public class ViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    ImageModel model = null;
    Appendable output = new StringBuilder();
    ImageView view = new ImageViewImpl(model, output);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    ImageModel model = new ImageModelImpl();
    Appendable output = null;
    ImageView view = new ImageViewImpl(model, output);
  }

  @Test
  public void testRenderMessage() {
    ImageModel model = new ImageModelImpl();
    Appendable output = new StringBuilder();
    ImageView view = new ImageViewImpl(model, output);
    try {
      view.renderMessage("load command successful");
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
    assertEquals("load command successful", output.toString());
  }

  @Test(expected = IOException.class)
  public void testFakeAppendableRenderMessage() throws IOException {
    Appendable fakeAppendable = new FakeAppendableTest();
    MockModel mock = new MockModel(fakeAppendable);
    ImageView view = new ImageViewImpl(mock, fakeAppendable);
    view.renderMessage("hi");
  }
}
