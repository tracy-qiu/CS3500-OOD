package model;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;


import static org.junit.Assert.assertEquals;

public class EnglishSolitaireModelTest {


  SlotState[][] defaultBoard =
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

  SlotState[][] oneThicknessBoard = {{SlotState.Empty}};

  SlotState[][] fiveThicknessBoard =
          {{SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Marble,
                  SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                  SlotState.Invalid, SlotState.Invalid, SlotState.Invalid},
                  {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Invalid, SlotState.Invalid, SlotState.Invalid},
                  {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Invalid, SlotState.Invalid, SlotState.Invalid},
                  {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble},
                  {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble},
                  {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Empty, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble},
                  {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble},
                  {SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble},
                  {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Invalid, SlotState.Invalid, SlotState.Invalid},
                  {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Invalid, SlotState.Invalid, SlotState.Invalid},
                  {SlotState.Invalid, SlotState.Invalid, SlotState.Invalid, SlotState.Marble,
                          SlotState.Marble, SlotState.Marble, SlotState.Marble, SlotState.Marble,
                          SlotState.Invalid, SlotState.Invalid, SlotState.Invalid}};

  @Test
  public void testCreateValidModelConstructor1() {
    MarbleSolitaireModelState ModelState = new EnglishSolitaireModel();
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelConstructorDisablesNegativeCellPosition() {
    new EnglishSolitaireModel(-3, 5);
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
  public void testCreateValidModelConstructor4EvenThickness() {
    new EnglishSolitaireModel(4, 0, 4);
  }

  @Test
  public void testInitializeBoardThreeThickness() {
    MarbleSolitaireModelState model = new EnglishSolitaireModel();
    SlotState[][] board = model.initializeBoard();
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(board[row][col], defaultBoard[row][col]);
      }
    }
  }

  @Test
  public void testInitializeBoardOneThickness() {
    MarbleSolitaireModelState model = new EnglishSolitaireModel(1);
    SlotState[][] board = model.initializeBoard();
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(board[row][col], oneThicknessBoard[row][col]);
      }
    }
  }

  @Test
  public void testInitializeBoardFiveThickness() {
    MarbleSolitaireModelState model = new EnglishSolitaireModel(5);
    SlotState[][] board = model.initializeBoard();
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        assertEquals(board[row][col], fiveThicknessBoard[row][col]);
      }
    }
  }

  @Test
  public void testMove() {
  }

  @Test
  public void testIsGameOver() {
  }

  @Test
  public void testGetBoardSize() {
  }

  @Test
  public void testGetSlotAt() {
  }

  @Test
  public void testGetScore() {
  }
}