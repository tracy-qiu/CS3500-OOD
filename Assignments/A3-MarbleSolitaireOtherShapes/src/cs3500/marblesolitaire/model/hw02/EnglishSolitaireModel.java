package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * This class implements MarbleSolitaireModel and represents the operations offered by the English
 * marble solitaire model.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * English solitaire model constructor that creates default board with arm thickness 3, and empty
   * cell at (3.3).
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
    this.boardState = this.initializeBoard();
  }

  /**
   * English solitaire model constructor that creates board with arm thickness 3, and empty
   * cell at specified (sRow, sCol) position.
   *
   * @param sRow row coordinate of empty slot
   * @param sCol row coordinate of empty slot
   * @throws IllegalArgumentException if invalid cell position
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
    int center = this.getBoardSize() / 2;
    int margin = this.armThickness / 2;
    if (!((sRow >= center - margin && sRow <= center + margin && sCol >= 0
            && sCol < this.getBoardSize())
            || (sCol >= center - margin && sCol <= center + margin && sRow >= 0
            && sRow < this.getBoardSize()))) {
      throw new IllegalArgumentException(String.format("Invalid cell position " + "%d " + "%d",
              sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.boardState = this.initializeBoard();
  }

  /**
   * English solitaire model constructor that creates board with specified arm thickness, and empty
   * cell at (3, 3) position.
   *
   * @param armThickness number of marbles in the top row
   * @throws IllegalArgumentException if invalid arm thickness
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (3 * armThickness - 2) / 2, (3 * armThickness - 2) / 2);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;
    this.boardState = this.initializeBoard();
  }

  /**
   * English solitaire model constructor that creates board with specified arm thickness, and empty
   * cell at specified (sRow, sCol) position.
   *
   * @param armThickness number of marbles in the top row
   * @param sRow         row coordinate of empty slot
   * @param sCol         col coordinate of empty slot
   * @throws IllegalArgumentException if invalid cell position and invalid arm thickness.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid even arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;

    int center = this.getBoardSize() / 2;
    int margin = this.armThickness / 2;
    if (!((sRow >= center - margin && sRow <= center + margin && sCol >= 0
            && sCol < this.getBoardSize())
            || (sCol >= center - margin && sCol <= center + margin && sRow >= 0
            && sRow < this.getBoardSize()))) {
      throw new IllegalArgumentException(String.format("Invalid cell position " + "%d " + "%d",
              sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.boardState = this.initializeBoard();
  }


  protected boolean invalidSpot(int row, int col) {
    // top left
    return row < armThickness - 1 && col < armThickness - 1
            // top right
            || row < armThickness - 1 && col >= 2 * armThickness - 1
            // bottom left
            || row >= 2 * armThickness - 1 && col < armThickness - 1
            // bottom right
            || row >= 2 * armThickness - 1 && col >= 2 * armThickness - 1;
  }
}
