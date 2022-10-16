package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class implements MarbleSolitaireView and represents the operations offered by the marble
 * solitaire text view.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private MarbleSolitaireModelState model;

  private Appendable output;

  /**
   * Marble solitaire text view constructor that creates a view model.
   *
   * @param model marble solitaire model to be used to create view
   * @throws IllegalArgumentException if given marble solitaire model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null marble solitaire model"));
    }
    this.model = model;
    this.output = System.out;
  }

  /**
   * Marble solitaire text view constructor that creates a view from the model and takes in an
   * appendable to append messages and the board to.
   *
   * @param model  MarbleSolitaireModelState that the view communicates with the render model
   *                details
   * @param output Appendable output that gets added to when board or message are rendered
   * @throws IllegalArgumentException if the model or output are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable output) throws
          IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null marble solitaire model"));
    }
    this.model = model;

    if (output == null) {
      throw new IllegalArgumentException(String.format("Null marble solitaire appendable"));
    }
    this.output = output;
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

  @Override
  public void renderBoard() throws IOException {
    try {
      this.output.append(this.toString());
    } catch (IOException e) {
      throw new IOException(String.format("Render board failed " + e));
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IOException(String.format("Render message failed " + e));
    }
  }

}
