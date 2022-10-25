package controller;

import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods and edge cases of the MarbleSolitaireControllerImpl.
 */
public class ControllerImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Appendable input = new StringBuilder("hello 3 hello1 hello -48 2 q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(null, controllerOutput);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(null, view,
            userInput);
    controller.playGame();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("1 2 32 4 q");
    Readable userInput = new StringReader(input.toString());
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, null,
            userInput);
    controller.playGame();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInput() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            null);
    controller.playGame();
  }

  @Test
  public void testQuitInput() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();

    Appendable input = new StringBuilder("q");
    Readable userInput = new StringReader(input.toString());

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            userInput);

    controller.playGame();

    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 32\nGame quit!\n"
            + "State of game when quit:\n    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 32\n", controllerOutput.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testNoMoreInputs() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();

    Appendable input = new StringBuilder("6 4 4 4  3 4 5 4");
    Readable userInput = new StringReader(input.toString());

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            userInput);

    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testNoMoreInputs2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();

    Appendable input = new StringBuilder("6 hello 4 4 41 4 34 3 4 world 5 4");
    Readable userInput = new StringReader(input.toString());

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            userInput);

    controller.playGame();
  }

  @Test
  public void testValidMoveWithQuit() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();

    Appendable input = new StringBuilder("2 4 4 4 q");
    Readable userInput = new StringReader(input.toString());

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            userInput);

    controller.playGame();

    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 32\n    O O O\n    O _ O\n"
            + "O O O _ O O O\nO O O O O O O\nO O O O O O O\n    O O O\n    O O O\nScore: 31\n"
            + "Game quit!\nState of game when quit:"
            + "\n    O O O\n    O _ O\nO O O _ O O O\nO O O O O O O\nO O O O O O O\n"
            + "    O O O\n    O O O\nScore: 31\n", controllerOutput.toString());
  }

  @Test
  public void testValidMoveWithQuit2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();

    Appendable input = new StringBuilder("-2 2 4 -3 -90 4 4 q");
    Readable userInput = new StringReader(input.toString());

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            userInput);

    controller.playGame();

    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
                    + "O O O O O O O\n    O O O\n    O O O\nScore: 32\n    O O O\n    O _ O\n"
                    + "O O O _ O O O\nO O O O O O O\nO O O O O O O\n    O O O\n    O O O\n"
                    + "Score: 31\nGame quit!\nState of game when quit:\n    O O O\n    O _ O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\nO O O O O O O\n    O O O\n    O O O\nScore: 31\n",
            controllerOutput.toString());
  }

  @Test
  public void testMoveUntilEndGame() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 next 3 4 5 4  1 4 3 4  4 2 4 4  4 5 4 3  "
            + "4 7 4 5");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();
    assertEquals(appendable.toString(), "Move method was called from (5, 3) (3, 3)\n"
            + "Move method was called from (2, 3) (4, 3)\n"
            + "Move method was called from (0, 3) (2, 3)\n"
            + "Move method was called from (3, 1) (3, 3)\n"
            + "Move method was called from (3, 4) (3, 2)\n"
            + "Move method was called from (3, 6) (3, 4)\n");

    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 32\n"
            + "    O O O\n    O O O\nO O O O O O O\nO O O O O O O\nO O O _ O O O\n"
            + "    O _ O\n    O O O\nScore: 31\n"
            + "    O O O\n    O O O\nO O O _ O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 30\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 29\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ _ O O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 28\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ _ O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 27\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ O _ _\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 26\n"
            + "Game over!\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ O _ _\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 26\n", controllerOutput.toString());
  }

  @Test
  public void testMoveUntilEndGamePlusOneMove() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4  3 4 5 4  1 4 3 4  4 2 4 4  4 5 4 3  4 7 4 5   "
            + "4 5 2 5");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();
    assertEquals(appendable.toString(), "Move method was called from (5, 3) (3, 3)\n"
            + "Move method was called from (2, 3) (4, 3)\n"
            + "Move method was called from (0, 3) (2, 3)\n"
            + "Move method was called from (3, 1) (3, 3)\n"
            + "Move method was called from (3, 4) (3, 2)\n"
            + "Move method was called from (3, 6) (3, 4)\n");

    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 32\n"
            + "    O O O\n    O O O\nO O O O O O O\nO O O O O O O\nO O O _ O O O\n"
            + "    O _ O\n    O O O\nScore: 31\n"
            + "    O O O\n    O O O\nO O O _ O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 30\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 29\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ _ O O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 28\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ _ O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 27\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ O _ _\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 26\n"
            + "Game over!\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ O _ _\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 26\n", controllerOutput.toString());
  }

  @Test
  public void testMoveUntilEndGameWithBadInputs() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("6 4 s 4 4 hello  3 hi 4 5 4  1 4 pizza 3 4  4 2 4 4  "
            + "five 4 5 4 3 -10 4 7 4 5");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("Move method was called from (5, 3) (3, 3)\n"
            + "Move method was called from (2, 3) (4, 3)\n"
            + "Move method was called from (0, 3) (2, 3)\n"
            + "Move method was called from (3, 1) (3, 3)\n"
            + "Move method was called from (3, 4) (3, 2)\n"
            + "Move method was called from (3, 6) (3, 4)\n", appendable.toString());

    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O\nScore: 32\n"
            + "    O O O\n    O O O\nO O O O O O O\nO O O O O O O\nO O O _ O O O\n"
            + "    O _ O\n    O O O\nScore: 31\n"
            + "    O O O\n    O O O\nO O O _ O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 30\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO O O _ O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 29\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ _ O O O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 28\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ _ O O\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 27\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ O _ _\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 26\n"
            + "Game over!\n"
            + "    O _ O\n    O _ O\nO O O O O O O\nO _ O _ O _ _\nO O O O O O O\n"
            + "    O _ O\n    O O O\nScore: 26\n", controllerOutput.toString());
  }

  @Test
  public void testMoveUntilEndGame2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4  5 2 5 4  4 4 6 4  2 4 4 4  3 2 3 4  4 4 2 4  "
            + "3 6 3 4  4 6 4 4  3 4 5 4  6 4 4 4  4 3 4 5  4 1 4 3  1 3 3 3  4 3 2 3  1 4 3 4  "
            + "1 5 3 5  4 5 2 5  5 6 5 4  7 3 5 3  5 3 5 5  6 5 4 5  7 5 7 3");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("Move method was called from (5, 3) (3, 3)\n"
            + "Move method was called from (4, 1) (4, 3)\n"
            + "Move method was called from (3, 3) (5, 3)\n"
            + "Move method was called from (1, 3) (3, 3)\n"
            + "Move method was called from (2, 1) (2, 3)\n"
            + "Move method was called from (3, 3) (1, 3)\n"
            + "Move method was called from (2, 5) (2, 3)\n"
            + "Move method was called from (3, 5) (3, 3)\n"
            + "Move method was called from (2, 3) (4, 3)\n"
            + "Move method was called from (5, 3) (3, 3)\n"
            + "Move method was called from (3, 2) (3, 4)\n"
            + "Move method was called from (3, 0) (3, 2)\n"
            + "Move method was called from (0, 2) (2, 2)\n"
            + "Move method was called from (3, 2) (1, 2)\n"
            + "Move method was called from (0, 3) (2, 3)\n"
            + "Move method was called from (0, 4) (2, 4)\n"
            + "Move method was called from (3, 4) (1, 4)\n"
            + "Move method was called from (4, 5) (4, 3)\n"
            + "Move method was called from (6, 2) (4, 2)\n"
            + "Move method was called from (4, 2) (4, 4)\n"
            + "Move method was called from (5, 4) (3, 4)\n"
            + "Move method was called from (6, 4) (6, 2)\n", appendable.toString());

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O _ _ _ O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O _ _ _ O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ _ O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ _ O O O\n"
            + "O O O _ O O O\n"
            + "O _ _ _ O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 26\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ O _ _ O\n"
            + "O O O _ O O O\n"
            + "O _ _ _ O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 25\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ O _ _ O\n"
            + "O O O O _ _ O\n"
            + "O _ _ _ O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 24\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ _ _ _ O\n"
            + "O O O _ _ _ O\n"
            + "O _ _ O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 23\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ _ _ _ O\n"
            + "O O O O _ _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 22\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ _ _ _ O\n"
            + "O O _ _ O _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 21\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ _ _ _ O\n"
            + "_ _ O _ O _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 20\n"
            + "    _ O O\n"
            + "    _ O O\n"
            + "O _ O _ _ _ O\n"
            + "_ _ O _ O _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 19\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "O _ _ _ _ _ O\n"
            + "_ _ _ _ O _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 18\n"
            + "    _ _ O\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ O _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 17\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "O _ _ O O _ O\n"
            + "_ _ _ _ O _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 16\n"
            + "    _ _ _\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "O _ _ _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 15\n"
            + "    _ _ _\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "O _ _ O _ _ O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 14\n"
            + "    _ _ _\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "O _ O O _ _ O\n"
            + "    _ _ O\n"
            + "    _ O O\n"
            + "Score: 13\n"
            + "    _ _ _\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "O _ _ _ O _ O\n"
            + "    _ _ O\n"
            + "    _ O O\n"
            + "Score: 12\n"
            + "    _ _ _\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ O _ O\n"
            + "O _ _ _ _ _ O\n"
            + "    _ _ _\n"
            + "    _ O O\n"
            + "Score: 11\n"
            + "    _ _ _\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ O _ O\n"
            + "O _ _ _ _ _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 10\n"
            + "Game over!\n"
            + "    _ _ _\n"
            + "    O _ O\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ O _ O\n"
            + "O _ _ _ _ _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 10\n", controllerOutput.toString());
  }

  @Test
  public void testInvalidInputsFullGame() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("6 4 s 4 4 hello  3 hi 4 5 4  1 4 pizza 3 4  4 2 4 4  "
            + "five 4 5 4 3  4 7 45 5 4 7 4 5  4 5 2 5");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O _ O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O _ O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O _ O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O _ O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ _ O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: Invalid 'from' or 'to' position 3 3 \n"
            + "    O _ O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 26\n"
            + "Game over!\n"
            + "    O _ O\n"
            + "    O _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 26\n", controllerOutput.toString());
  }

  @Test
  public void testInvalidInputsOneMove() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("pizza 4 6 45 2 4 6 4 4 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: Invalid 'from' or 'to' position 3 3 \n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n", controllerOutput.toString());
  }

  @Test
  public void testMoveInvalidFromSpotNoMarble() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("4 4 6 4 1 Q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: There is no marble at the specified 'from' "
            + "position \n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", controllerOutput.toString());
  }

  @Test
  public void testMoveInvalidFromSpotNoMarble2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(4, 6);
    Appendable input = new StringBuilder("6 6 4 6 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O _\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: There is no marble at the specified 'from' "
            + "position \n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O _\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", controllerOutput.toString());
  }

  @Test
  public void testMoveInvalidFromSpotOutOfBounds() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("8 4 6 4 1 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: Invalid 'from' or 'to' position 3 3 \n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", controllerOutput.toString());
  }

  @Test
  public void testMoveInvalidToSpotNotEmpty() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("4 7 6 7 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: The 'to' position is not empty \n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", controllerOutput.toString());
  }

  @Test
  public void testMoveInvalidMiddleSpotNoMarble() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable input = new StringBuilder("4 6 4 4  4 4 4 6 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: There is no marble in the slot between the 'to' "
            + "and 'from' positions \n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n", controllerOutput.toString());
  }

  @Test
  public void testMovesWithRandomEmptySlot() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 4);
    Appendable input = new StringBuilder("4 -3 3 4 5 6 rt q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O _ O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "Score: 32\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O _ _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "Score: 31\n"
                    + "Game quit!\n"
                    + "State of game when quit:\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O _ _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "Score: 31\n", controllerOutput.toString());
  }

  @Test
  public void testMovesWithRandomEmptySlot2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 4);
    Appendable input = new StringBuilder("4 -3 3 4 5 6 rt q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O _ O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "Score: 32\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O _ _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "Score: 31\n"
                    + "Game quit!\n"
                    + "State of game when quit:\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O _ _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "Score: 31\n", controllerOutput.toString());
  }


  @Test(expected = IllegalStateException.class)
  public void testFakeAppendable() throws IllegalStateException {
    Appendable fakeAppendable = new FakeAppendableTest();
    Readable input = new StringReader(fakeAppendable.toString());
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MockModel mock = new MockModel(model, fakeAppendable);
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, fakeAppendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, input);
    controller.playGame();
  }

  @Test
  public void testEuropeanUntilEndGame() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 3 4 5 4  1 4 3 4  4 2 4 4  4 5 4 3 4 7 4 5"
            + " 2 2 2 4 6 2 6 4 2 5 2 3  6 5 6 3  2 6 4 6  5 6 3 6  5 4 5 6  5 2 5 4  5 7 5 5 "
            + " 5 5 5 3  4 5 2 5  1 5 3 5");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 34\n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 33\n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O _ O _ _ O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O _ O\n"
            + "  _ _ O O O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O _ O\n"
            + "  _ _ O O O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  _ _ O O O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O _ O\n"
            + "  _ O _ _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  _ _ O O O\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "    O _ O\n"
            + "  _ O _ _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 26\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O _ O\n"
            + "O _ O _ O O _\n"
            + "O O O O O O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 25\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O _ O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 24\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O _ _ O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 23\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O _ _ O _ O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 22\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O _ _ O O _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 21\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 20\n"
            + "    O _ O\n"
            + "  _ O _ O _\n"
            + "O O O O _ O O\n"
            + "O _ O _ _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 19\n"
            + "    O _ _\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 18\n"
            + "Game over!\n"
            + "    O _ _\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 18\n", controllerOutput.toString());
  }

  @Test
  public void testEuropeanUntilEndGameWithBadInputs() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 djfkl 3 4 5 mkj 4 76 3 28 9  1 4 3 4  4 2 4 4"
            + "  4 5 4 3 4 7 4 5 2 2 2 4 6 2 6 4"
            + " 2 5 2 3  6 5 6 3  2 6 4 6 jlkjd 5 6 3 6  5 4 5 6  5 2 5 4  5 7 5 5  5 5 5 3  "
            + "4 5 2 5  1 5 3 5");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 34\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: Invalid 'from' or 'to' position 3 3 \n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 33\n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O _ O _ _ O O\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O _ O\n"
            + "  O O _ O O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O _ O\n"
            + "  _ _ O O O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O _ O\n"
            + "  _ _ O O O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  _ _ O O O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O _ O\n"
            + "  _ O _ _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  _ _ O O O\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "    O _ O\n"
            + "  _ O _ _ O\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 26\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O _ O\n"
            + "O _ O _ O O _\n"
            + "O O O O O O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 25\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O O O _ O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 24\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O O O _ _ O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 23\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O _ _ O _ O O\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 22\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O _ _ O O _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 21\n"
            + "    O _ O\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ O _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 20\n"
            + "    O _ O\n"
            + "  _ O _ O _\n"
            + "O O O O _ O O\n"
            + "O _ O _ _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 19\n"
            + "    O _ _\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 18\n"
            + "Game over!\n"
            + "    O _ _\n"
            + "  _ O _ _ _\n"
            + "O O O O O O O\n"
            + "O _ O _ _ _ _\n"
            + "O _ O _ _ _ _\n"
            + "  _ O _ _ O\n"
            + "    O O O\n"
            + "Score: 18\n", controllerOutput.toString());
  }

  @Test
  public void testEuropeanQinFromRowSpot() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n", controllerOutput.toString());
  }

  @Test
  public void testEuropeanQinFromColSpot() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 8 Q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n", controllerOutput.toString());
  }

  @Test
  public void testEuropeanQinToRowSpot() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 1 3 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n", controllerOutput.toString());
  }

  @Test
  public void testEuropeanQinToColSpot() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 2 3 4 Q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n", controllerOutput.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testEuropeanNoMoreInputs() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("6 4 4 4 4 4 ");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();
  }

  @Test
  public void testTriangleUntilEndGame() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable input = new StringBuilder("3 1 1 1  5 3 3 1  2 2 4 2  5 4 3 2  4 2 2 2  5 1 5 3"
            + "  3 1 5 1");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "    O\n"
            + "   _ O\n"
            + "  O O O\n"
            + " O _ O O\n"
            + "O O _ O O\n"
            + "Score: 12\n"
            + "    O\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O _ O O\n"
            + "Score: 11\n"
            + "    O\n"
            + "   _ _\n"
            + "  O O O\n"
            + " O O _ O\n"
            + "O O _ _ O\n"
            + "Score: 10\n"
            + "    O\n"
            + "   _ O\n"
            + "  O _ O\n"
            + " O _ _ O\n"
            + "O O _ _ O\n"
            + "Score: 9\n"
            + "    O\n"
            + "   _ O\n"
            + "  O _ O\n"
            + " O _ _ O\n"
            + "_ _ O _ O\n"
            + "Score: 8\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ _ O\n"
            + " _ _ _ O\n"
            + "O _ O _ O\n"
            + "Score: 7\n"
            + "Game over!\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ _ O\n"
            + " _ _ _ O\n"
            + "O _ O _ O\n"
            + "Score: 7\n", controllerOutput.toString());
  }

  @Test
  public void testTriangleUntilEndGameWithRandomInputs() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable input = new StringBuilder("3 1 1 1 h  5 3  hu 3 1   2 2 4 2  hello  43 2 3 1 5 4 3 2"
            + "  4 2 2 2 j 5 1 5 3  3 1 5 1");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "    O\n"
            + "   _ O\n"
            + "  O O O\n"
            + " O _ O O\n"
            + "O O _ O O\n"
            + "Score: 12\n"
            + "    O\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O _ O O\n"
            + "Score: 11\n"
            + "Invalid move. Play again.\n"
            + "java.lang.IllegalArgumentException: Invalid 'from' or 'to' position 0 0 \n"
            + "    O\n"
            + "   _ _\n"
            + "  O O O\n"
            + " O O _ O\n"
            + "O O _ _ O\n"
            + "Score: 10\n"
            + "    O\n"
            + "   _ O\n"
            + "  O _ O\n"
            + " O _ _ O\n"
            + "O O _ _ O\n"
            + "Score: 9\n"
            + "    O\n"
            + "   _ O\n"
            + "  O _ O\n"
            + " O _ _ O\n"
            + "_ _ O _ O\n"
            + "Score: 8\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ _ O\n"
            + " _ _ _ O\n"
            + "O _ O _ O\n"
            + "Score: 7\n"
            + "Game over!\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ _ O\n"
            + " _ _ _ O\n"
            + "O _ O _ O\n"
            + "Score: 7\n", controllerOutput.toString());
  }

  @Test
  public void testTriangleQinFromRowSpot() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable input = new StringBuilder("3 1 1 1 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n", controllerOutput.toString());
  }

  @Test
  public void testTriangleQinFromColSpot() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable input = new StringBuilder("3 1 1 1 4 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n", controllerOutput.toString());
  }

  @Test
  public void testTriangleQinToRowSpot() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable input = new StringBuilder("3 1 1 1 4 3 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n", controllerOutput.toString());
  }

  @Test
  public void testTriangleQinToColSpot() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable input = new StringBuilder("3 1 1 1 4 7 q");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new TriangleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();

    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n", controllerOutput.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testTriangleNoMoreInputs() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    Appendable input = new StringBuilder("3 1 1 1 1 1 ");
    Readable userInput = new StringReader(input.toString());

    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel mock = new MockModel(model, appendable);

    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, view, userInput);

    controller.playGame();
  }

}
