package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract Solitaire text view that abstracts the common methods and fields of the english,
 * european, and triangle text views.
 */
public abstract class AbstractSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState model;

  protected Appendable output;


  /**
   * Marble solitaire text view constructor that creates a view from the model and takes in an
   * appendable to append messages and the board to.
   *
   * @param model  MarbleSolitaireModelState that the view communicates with the render model
   *               details
   * @param output Appendable output that gets added to when board or message are rendered
   * @throws IllegalArgumentException if the model or output are null
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState model, Appendable output) throws
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
  public abstract String toString();

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
