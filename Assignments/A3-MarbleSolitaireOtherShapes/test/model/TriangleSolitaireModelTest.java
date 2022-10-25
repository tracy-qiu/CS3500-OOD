package model;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for triangle solitaire model, testing its methods and constructors.
 */
public class TriangleSolitaireModelTest {
  MarbleSolitaireModel model1 = new TriangleSolitaireModel();
  MarbleSolitaireModel model2 = new TriangleSolitaireModel(4);
  MarbleSolitaireModel model3 = new TriangleSolitaireModel(2, 1);
  MarbleSolitaireModel model4 = new TriangleSolitaireModel(3, 1, 0);

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativeRowPosition() {
    new TriangleSolitaireModel(-3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativeColPosition() {
    new TriangleSolitaireModel(2, -5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativePosition() {
    new TriangleSolitaireModel(-1, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesInvalidCellPosition() {
    new TriangleSolitaireModel(4, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativeArmThickness() {
    new TriangleSolitaireModel(-1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testCreateValidModelConstructor4InvalidCell() {
    new TriangleSolitaireModel(9, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateValidModelConstructor4InvalidNegativeRowCell() {
    new TriangleSolitaireModel(5, -2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateValidModelConstructor4InvalidNegativeColCell() {
    new TriangleSolitaireModel(6, 2, -1);
  }

  SlotState[][] defaultBoard =
      {{SlotState.Empty, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble}};

  SlotState[][] board2 =
      {{SlotState.Empty, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble}};

  SlotState[][] board3 =
      {{SlotState.Marble, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Marble, SlotState.Empty, SlotState.Marble, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble}};

  SlotState[][] board4 =
      {{SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Empty, SlotState.Marble, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble}};

  // test initializing board using constructor 1 - default board
  @Test
  public void testInitializeBoardConstructor1() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), defaultBoard[row][col]);
      }
    }
  }

  // test initializing board using constructor 2 - specified arm thickness
  @Test
  public void testInitializeBoardConstructor2() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(4);
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), board2[row][col]);
      }
    }
  }

  //test initializing board using constructor 3 with specified arm thickness
  @Test
  public void testInitializeBoardConstructor3() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(2, 1);
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), board3[row][col]);
      }
    }
  }

  //test initializing board using constructor 4 with specified arm thickness and arm thickness
  @Test
  public void testInitializeBoardConstructor4() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(3, 1, 0);
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), board4[row][col]);
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisableInvalidOutOfBoundsMovePosition() {
    model3.move(0, 2, 3, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisableInvalidNegativeMovePosition() {
    model3.move(1, 2, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisableInvalidMoveToPosition() {
    model3.move(0, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisableInvalidMoveFromEmptyPosition() {
    model3.move(3, 3, 3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisableInvalidMoveFromPosition() {
    model3.move(1, 5, 1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisableNot2AwayFromMovePosition() {
    model3.move(2, 4, 2, 3);
  }

  // test valid move positions
  @Test
  public void testValidMovePositions() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(5, 3, 3, 3);
    assertEquals(model.getSlotAt(5, 3), SlotState.Empty);
    assertEquals(model.getSlotAt(4, 3), SlotState.Empty);
    assertEquals(model.getSlotAt(3, 3), SlotState.Marble);

    model.move(4, 1, 4, 3);
    assertEquals(model.getSlotAt(4, 1), SlotState.Empty);
    assertEquals(model.getSlotAt(4, 2), SlotState.Empty);
    assertEquals(model.getSlotAt(4, 3), SlotState.Marble);
    assertEquals(model.getSlotAt(5, 6), SlotState.Invalid);
  }

  // test if game is over
  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel model0 = new TriangleSolitaireModel(1);
    assertTrue(model0.isGameOver());
    assertFalse(model1.isGameOver());
    assertFalse(model2.isGameOver());
    assertFalse(model3.isGameOver());

    MarbleSolitaireModel model = new TriangleSolitaireModel();
    model.move(2, 0, 0, 0);
    model.move(4, 2, 2, 0);
    model.move(1, 1, 3, 1);
    assertFalse(model.isGameOver());
    model.move(4, 3, 2, 1);
    model.move(3, 1,1,1);
    model.move(4, 0, 4, 2);
    model.move(2, 0, 4, 0);
    assertTrue(model.isGameOver());

  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, model1.getBoardSize());
    assertEquals(4, model2.getBoardSize());
    assertEquals(5, model3.getBoardSize());
    assertEquals(3, model4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, model1.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, model3.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, model3.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, model4.getSlotAt(1, 0));
    assertEquals(SlotState.Invalid, model2.getSlotAt(1, 2));
  }

  @Test
  public void testGetScore() {
    assertEquals(14, model1.getScore());
    assertEquals(9, model2.getScore());
    assertEquals(14, model3.getScore());
    assertEquals(5, model4.getScore());

    MarbleSolitaireModel model = new TriangleSolitaireModel();
    model.move(2, 0, 0, 0);
    model.move(4, 2, 2, 0);
    model.move(1, 1, 3, 1);
    assertEquals(11, model.getScore());
    model.move(4, 3, 2, 1);
    model.move(3, 1,1,1);
    model.move(4, 0, 4, 2);
    model.move(2, 0, 4, 0);
    assertEquals(7, model.getScore());
  }
}
