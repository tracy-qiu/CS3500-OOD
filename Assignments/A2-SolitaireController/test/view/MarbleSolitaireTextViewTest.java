package view;

import org.junit.Test;

import java.io.IOException;

import controller.FakeAppendableTest;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods and constructor of the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {

  MarbleSolitaireView model = new MarbleSolitaireTextView(new EnglishSolitaireModel());

  MarbleSolitaireView model2 =
          new MarbleSolitaireTextView(new EnglishSolitaireModel(0, 2));

  MarbleSolitaireView model3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(1));

  MarbleSolitaireView model4 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5));


  @Test(expected = IllegalArgumentException.class)
  public void viewConstructorDisablesNullModel() {
    new MarbleSolitaireTextView(null);
  }

  //  test toString that creates text view of model
  @Test
  public void testToString() {
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O", model.toString());
    assertEquals("    _ O O\n    O O O\nO O O O O O O\nO O O O O O O\n"
            + "O O O O O O O\n    O O O\n    O O O", model2.toString());
    assertEquals("_", model3.toString());
    assertEquals("        O O O O O\n        O O O O O\n        O O O O O\n        "
                    + "O O O O O\nO O O O O O O O O O O O O\nO O O O O O O O O O O O O\n"
                    + "O O O O O O _ O O O O O O\nO O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n        O O O O O\n        O O O O O\n        "
                    + "O O O O O\n        O O O O O", model4.toString());
  }

  @Test
  public void testRenderBoard() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable output = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);

    try {
      view.renderBoard();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O", output.toString());
  }

  @Test
  public void testRenderMessage() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable output = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
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
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MockModel mock = new MockModel(model, fakeAppendable);
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, fakeAppendable);
    view.renderBoard();
  }

  @Test(expected = IOException.class)
  public void testFakeAppendableRenderMessage() throws IOException {
    Appendable fakeAppendable = new FakeAppendableTest();
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MockModel mock = new MockModel(model, fakeAppendable);
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, fakeAppendable);
    view.renderMessage("hi");
  }
}