package view;

import org.junit.Test;

import java.io.IOException;

import controller.FakeAppendableTest;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for triangle solitaire methods and constructors.
 */
public class TriangleSolitaireTextViewTest {
  MarbleSolitaireView triangleModel1 = new TriangleSolitaireTextView(new TriangleSolitaireModel());

  MarbleSolitaireView triangleModel2 = new TriangleSolitaireTextView(
          new TriangleSolitaireModel(6));
  MarbleSolitaireView triangleModel3 = new TriangleSolitaireTextView(
          new TriangleSolitaireModel(2, 1));
  MarbleSolitaireView triangleModel4 = new TriangleSolitaireTextView(
          new TriangleSolitaireModel(7, 6, 3));


  @Test
  public void testToString() {
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", triangleModel1.toString());
    assertEquals("     _\n"
            + "    O O\n"
            + "   O O O\n"
            + "  O O O O\n"
            + " O O O O O\n"
            + "O O O O O O", triangleModel2.toString());
    assertEquals("    O\n"
            + "   O O\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O", triangleModel3.toString());
    assertEquals("      O\n"
            + "     O O\n"
            + "    O O O\n"
            + "   O O O O\n"
            + "  O O O O O\n"
            + " O O O O O O\n"
            + "O O O _ O O O", triangleModel4.toString());
  }

  @Test
  public void testRenderBoard() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable output = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, output);

    try {
      view.renderBoard();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", output.toString());
  }

  @Test
  public void testRenderMessage() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable output = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, output);
    try {
      view.renderMessage("End game!");
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
    assertEquals("End game!", output.toString());
  }


  @Test(expected = IOException.class)
  public void testFakeAppendableRenderBoard() throws IOException {
    Appendable fakeAppendable = new FakeAppendableTest();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MockModel mock = new MockModel(model, fakeAppendable);
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, fakeAppendable);
    view.renderBoard();
  }

  @Test(expected = IOException.class)
  public void testFakeAppendableRenderMessage() throws IOException {
    Appendable fakeAppendable = new FakeAppendableTest();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MockModel mock = new MockModel(model, fakeAppendable);
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, fakeAppendable);
    view.renderMessage("hi");
  }
}
