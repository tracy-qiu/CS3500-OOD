package model;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

public class EnglishSolitaireModelTest {

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
  public void testCreateValidModelConstructor4() {

  }

  @Test
  public void testInitializeBoard() {

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