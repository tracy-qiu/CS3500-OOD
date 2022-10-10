package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState model = new EnglishSolitaireModel();

  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null marble solitaire model"));
    }
  }

  @Override
  public String toString() {
    String stringView = "";
    for (int row = 0; row < model.getBoardSize(); row++) {
      for (int col = 0; col < model.getBoardSize(); col++) {
        if (model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Invalid) {
          if (col < model.getBoardSize() / 2) {
            stringView += "  ";
          }
        } else if ((model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble)) {
          stringView += " 0";
        } else if ((model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty)) {
          stringView += " _";
        }
      }
      stringView += "\n";
    }
    stringView = stringView.substring(0, stringView.length() - 1);
    return stringView;
  }
}
