package cs3500.marblesolitaire.model.hw04;

/**
 * European Solitaire Model that controls the state of the game with moves, score, creating board.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * European solitaire model constructor that creates default board with arm thickness 3, and empty
   * cell at (3.3).
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
    this.boardState = this.initializeBoard();
  }

  /**
   * European solitaire model constructor that creates board with arm thickness 3, and empty
   * cell at specified (sRow, sCol) position.
   *
   * @param sRow row coordinate of empty slot
   * @param sCol row coordinate of empty slot
   * @throws IllegalArgumentException if invalid cell position
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
    if (!this.withinBounds(sRow, sCol) || this.invalidSpot(sRow, sCol)) {
      throw new IllegalArgumentException(String.format("Invalid cell position "
              + "%d " + "%d", sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.boardState = this.initializeBoard();
  }

  /**
   * European solitaire model constructor that creates board with specified arm thickness, and empty
   * cell at (3, 3) position.
   *
   * @param armThickness number of marbles in the top row
   * @throws IllegalArgumentException if invalid arm thickness
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (3 * armThickness - 2) / 2, (3 * armThickness - 2) / 2);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;
    this.boardState = this.initializeBoard();
  }

  /**
   * European solitaire model constructor that creates board with specified arm thickness, and empty
   * cell at specified (sRow, sCol) position.
   *
   * @param armThickness number of marbles in the top row
   * @param sRow         row coordinate of empty slot
   * @param sCol         col coordinate of empty slot
   * @throws IllegalArgumentException if invalid cell position and invalid arm thickness.
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid even arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;

    if (!this.withinBounds(sRow, sCol) || this.invalidSpot(sRow, sCol)) {
      throw new IllegalArgumentException(String.format("Invalid cell position "
              + "%d " + "%d", sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.boardState = this.initializeBoard();
  }


  protected boolean invalidSpot(int row, int col) {
    // top left
    return row < armThickness && col < armThickness - 1 && row + col < armThickness - 1
            // top right
            || row < armThickness && col >= 2 * armThickness - 1
            && this.getBoardSize() - col + row < armThickness
            // bottom left
            || row >= 2 * armThickness - 1 && col < armThickness - 1
            && this.getBoardSize() - row + col < armThickness
            // bottom right
            || row >= 2 * armThickness - 1 && col >= 2 * armThickness - 1
            && 2 * this.getBoardSize() - col - row <= armThickness;
  }
}
