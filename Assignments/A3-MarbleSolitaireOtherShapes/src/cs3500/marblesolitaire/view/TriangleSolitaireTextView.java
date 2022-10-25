package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Triangle solitaire text view class that extends the abstract solitaire text view.
 * Overwrites the toString method from the abstract class.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Triangle solitaire text view that only takes in a model and outputs System.out.
   *
   * @param model MarbleSolitaireModelState used to create view
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model, System.out);
  }

  /**
   * Triangle solitaire text view that takes in a model and outputs appendable. Game state is
   * recorded through the output.
   *
   * @param model  MarbleSolitaireModelState used to create view
   * @param output The output of the board and messages appended to the output to display to user
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable output) {
    super(model, output);
  }

  /**
   * Override the MarbleSolitaireTextView toString and formats the game board
   * with spots diagonal to each other.
   *
   * @return String output of the board
   */
  @Override
  public String toString() {
    String stringView = "";
    for (int row = 0; row < model.getBoardSize(); row++) {
      String rowString = "";
      for (int col = 0; col < model.getBoardSize(); col++) {
        if (model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Invalid) {
          rowString += "  ";
        } else if ((model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble)) {
          rowString += "O ";
        } else if ((model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty)) {
          rowString += "_ ";
        }
      }
      stringView += " ".repeat(this.model.getBoardSize() - row - 1);
      stringView += rowString.trim();
      stringView += "\n";
    }
    return stringView.substring(0, stringView.length() - 1);
  }
}
