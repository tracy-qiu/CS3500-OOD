package cs3500.marblesolitaire.model.hw02;

import java.io.IOException;

/**
 *  This class defines MockModel which is used as a mock of a MarbleSolitaireModel to confirm that
 *  the moves are being transmitted accurately through the model to the controller.
 */
public class MockModel implements MarbleSolitaireModel {

  private final Appendable appendable;

  private MarbleSolitaireModel model;

  /**
   * MockModel constructor that takes a model and output appendable so that the moves can be logged
   * and transmitted to the controller.
   * @param model MarbleSolitaireModel used for methods to be replicated
   * @param appendable output appendable that get added to each time move is called
   * @throws IllegalArgumentException if the appendable or model are null
   */
  public MockModel(MarbleSolitaireModel model, Appendable appendable) throws
          IllegalArgumentException {
    if (appendable == null) {
      throw new IllegalArgumentException(String.format("Null mock model appendable"));
    }
    this.appendable = appendable;
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null mock model appendable"));
    }
    this.model = model;

  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    try {
      this.appendable.append(String.format("Move method was called from (%d, %d) (%d, %d)\n",
              fromRow, fromCol, toRow, toCol));
      this.model.move(fromRow, fromCol, toRow, toCol);
    } catch (IOException e) {
      throw new IllegalArgumentException(String.format("Failed to append move string"));
    }
  }

  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  @Override
  public int getBoardSize() {
    return this.model.getBoardSize();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return this.model.getSlotAt(row, col);
  }

  @Override
  public int getScore() {
    return this.model.getScore();
  }
}
