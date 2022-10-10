package view;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.*;

public class MarbleSolitaireTextViewTest {

  MarbleSolitaireView model = new MarbleSolitaireTextView(new EnglishSolitaireModel());

  @Test(expected = IllegalArgumentException.class)
  public void viewConstructorDisablesNullModel() {
    new MarbleSolitaireTextView(null);
  }

  @Test
  public void testToString() {
    String view = model.toString();
    assertEquals("     0 0 0\n     0 0 0\n 0 0 0 0 0 0 0\n 0 0 0 _ 0 0 0\n " +
            "0 0 0 0 0 0 0\n     0 0 0\n     0 0 0", view);
  }
}