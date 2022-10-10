package model;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the methods and constructors of the English Solitaire Model class.
 */
public class EnglishSolitaireModelTest {

  MarbleSolitaireModel model1 = new EnglishSolitaireModel(1);
  MarbleSolitaireModel model3 = new EnglishSolitaireModel();
  MarbleSolitaireModel model5 = new EnglishSolitaireModel(5, 4, 7);
  MarbleSolitaireModel model7 = new EnglishSolitaireModel(7);

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativeRowPosition() {
    new EnglishSolitaireModel(-3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativeColPosition() {
    new EnglishSolitaireModel(2, -5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativePosition() {
    new EnglishSolitaireModel(-1, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesInvalidCellPosition() {
    new EnglishSolitaireModel(4, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativeArmThickness() {
    new EnglishSolitaireModel(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesEvenArmThickness() {
    new EnglishSolitaireModel(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateValidModelConstructor4InvalidCell() {
    new EnglishSolitaireModel(3, 0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateValidModelConstructor4InvalidNegativeCell() {
    new EnglishSolitaireModel(5, -2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateValidModelConstructor4EvenThickness() {
    new EnglishSolitaireModel(4, 0, 4);
  }

  SlotState[][] oneThicknessBoard = {{SlotState.Empty}};
  SlotState[][] threeThicknessBoard =
      {{SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Empty,
          SlotState.Marble, SlotState.Marble, SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid}};
  SlotState[][] fiveThicknessBoard =
      {{SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Empty, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid, SlotState.Invalid,
          SlotState.Invalid}};

  SlotState[][] threeThicknessBoard2 =
      {{SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Empty,
          SlotState.Marble, SlotState.Marble, SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble},
          {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Marble, SlotState.Marble},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid},
          {SlotState.Invalid, SlotState.Invalid, SlotState.Marble, SlotState.Marble,
          SlotState.Marble, SlotState.Invalid, SlotState.Invalid}};

  // test initializing board using constructor 1 - default board
  @Test
  public void testInitializeBoardConstructor1() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), threeThicknessBoard[row][col]);
      }
    }
  }

  // test initializing board using constructor 2 with specified empty slot
  @Test
  public void testInitializeBoardConstructor2() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(2, 3);
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), threeThicknessBoard2[row][col]);
      }
    }
  }

  //test initializing board using constructor 3 with specified arm thickness
  @Test
  public void testInitializeBoardConstructor3() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(5);
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), fiveThicknessBoard[row][col]);
      }
    }
  }

  //test initializing board using constructor 4 with specified arm thickness and empty slot
  @Test
  public void testInitializeBoardConstructor4() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(1, 0, 0);
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(model.getSlotAt(row, col), oneThicknessBoard[row][col]);
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
    assertTrue(model1.isGameOver());
    assertFalse(model3.isGameOver());
    assertFalse(model5.isGameOver());
    assertFalse(model7.isGameOver());

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(5, 3, 3, 3);
    model.move(4, 1, 4, 3);
    model.move(3, 3, 5, 3);
    model.move(1, 3, 3, 3);
    model.move(2, 1, 2, 3);
    model.move(3, 3, 1, 3);
    model.move(2, 5, 2, 3);
    assertFalse(model.isGameOver());
    model.move(3, 5, 3, 3);
    model.move(2, 3, 4, 3);
    model.move(5, 3, 3, 3);
    model.move(3, 2, 3, 4);
    model.move(3, 0, 3, 2);
    model.move(0, 2, 2, 2);
    model.move(3, 2, 1, 2);
    model.move(0, 3, 2, 3);
    model.move(0, 4, 2, 4);
    assertFalse(model.isGameOver());
    model.move(3, 4, 1, 4);
    model.move(4, 5, 4, 3);
    model.move(6, 2, 4, 2);
    model.move(4, 2, 4, 4);
    model.move(5, 4, 3, 4);
    model.move(6, 4, 6, 2);
    assertTrue(model.isGameOver());

  }

  @Test
  public void testGetBoardSize() {
    assertEquals(1, model1.getBoardSize());
    assertEquals(7, model3.getBoardSize());
    assertEquals(13, model5.getBoardSize());
    assertEquals(19, model7.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, model1.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, model3.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, model3.getSlotAt(5, 3));
    assertEquals(SlotState.Empty, model5.getSlotAt(4, 7));
    assertEquals(SlotState.Invalid, model7.getSlotAt(3, 2));
  }

  @Test
  public void testGetScore() {
    assertEquals(0, model1.getScore());
    assertEquals(32, model3.getScore());
    assertEquals(104, model5.getScore());

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(5, 3, 3, 3);
    model.move(4, 1, 4, 3);
    assertEquals(30, model.getScore());
    model.move(3, 3, 5, 3);
    model.move(1, 3, 3, 3);
    model.move(2, 1, 2, 3);
    model.move(3, 3, 1, 3);
    model.move(2, 5, 2, 3);
    assertEquals(25, model.getScore());
    model.move(3, 5, 3, 3);
    model.move(2, 3, 4, 3);
    model.move(5, 3, 3, 3);
    model.move(3, 2, 3, 4);
    model.move(3, 0, 3, 2);
    model.move(0, 2, 2, 2);
    model.move(3, 2, 1, 2);
    model.move(0, 3, 2, 3);
    model.move(0, 4, 2, 4);
    assertEquals(16, model.getScore());
    model.move(3, 4, 1, 4);
    model.move(4, 5, 4, 3);
    model.move(6, 2, 4, 2);
    model.move(4, 2, 4, 4);
    model.move(5, 4, 3, 4);
    model.move(6, 4, 6, 2);
    assertEquals(10, model.getScore());
  }
}