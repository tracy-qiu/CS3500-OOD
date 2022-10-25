package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class implements MarbleSolitaireView and represents the operations offered by the marble
 * solitaire text view.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Marble solitaire text view constructor that creates a view model.
   *
   * @param model marble solitaire model to be used to create view
   * @throws IllegalArgumentException if given marble solitaire model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable output) {
    super(model, output);
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model, System.out);
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
          stringView += "O ";
        } else if ((model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty)) {
          stringView += "_ ";
        }
      }
      stringView = stringView.substring(0, stringView.length() - 1);
      stringView += "\n";
    }
    return stringView.substring(0, stringView.length() - 1);
  }

}
