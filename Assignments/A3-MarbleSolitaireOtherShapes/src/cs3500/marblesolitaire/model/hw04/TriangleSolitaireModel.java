package cs3500.marblesolitaire.model.hw04;

/**
 * Triangle Solitaire Model that controls the state of the game with moves, score, creating board.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * Triangle solitaire model constructor that creates default board with arm thickness 5, and empty
   * cell at (0,0).
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
    this.boardState = this.initializeBoard();
  }

  /**
   * Triangle solitaire model constructor that creates board with arm thickness 5, and empty
   * cell at specified (sRow, sCol) position.
   *
   * @param sRow row coordinate of empty slot
   * @param sCol row coordinate of empty slot
   * @throws IllegalArgumentException if invalid cell position
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);
    if (!this.withinBounds(sRow, sCol) || this.invalidSpot(sRow, sCol)) {
      throw new IllegalArgumentException(String.format("Invalid cell position " + "%d " + "%d",
              sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.boardState = this.initializeBoard();
  }

  /**
   * Triangle solitaire model constructor that creates board with specified arm thickness, and empty
   * cell at (0, 0) position.
   *
   * @param armThickness number of marbles in the top row
   * @throws IllegalArgumentException if arm thickness is less  than 1
   */
  public TriangleSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, 0, 0);
    this.boardState = this.initializeBoard();
  }

  /**
   * Triangle solitaire model constructor that creates board with specified arm thickness, and empty
   * cell at specified (sRow, sCol) position.
   *
   * @param armThickness number of marbles in the top row
   * @param sRow         row coordinate of empty slot
   * @param sCol         col coordinate of empty slot
   * @throws IllegalArgumentException if invalid cell position and invalid arm thickness.
   */
  public TriangleSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);

    if (!this.withinBounds(sRow, sCol) || this.invalidSpot(sRow, sCol)) {
      throw new IllegalArgumentException(String.format("Invalid out of bounds cell position " + "%d"
                      + " " + "%d", sRow, sCol));
    }

    this.sRow = sRow;
    this.sCol = sCol;
    this.boardState = this.initializeBoard();
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Check valid "from" and "to" positions
    if (!(withinBounds(fromRow, fromCol) && withinBounds(toRow, toCol))) {
      throw new IllegalArgumentException(String.format("Invalid 'from' or 'to' position " + "%d "
              + "%d", sRow, sCol));
    }
    // There is marble at the specified "from" position
    else if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException(String.format("There is no marble at the specified 'from' "
              + "position"));
    }
    // The "to" position is empty
    else if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException(String.format("The 'to' position is not empty"));
    }
    // The "to" and "from" positions are exactly two positions away diagonally
    else if (!this.isDiagonalMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException(String.format("The 'to' and 'from' positions are not"
              + " exactly two positions away diagonally"));
    }
    // There is a marble in the slot between the 'to' and 'from' positions
    else if (this.getSlotAt((fromRow + toRow) / 2,
            (fromCol + toCol) / 2) != SlotState.Marble) {
      throw new IllegalArgumentException(String.format("There is no marble in the slot between"
              + "the 'to' and 'from' positions"));
    }
    this.boardState[fromRow][fromCol] = SlotState.Empty;
    this.boardState[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
    this.boardState[toRow][toCol] = SlotState.Marble;
  }

  /**
   * Checks if the "from spot" is diagonal or on the same row and two spots away from the "to spot".
   *
   * @param fromRow row of from spot
   * @param fromCol col of from spot
   * @param toRow   row of to spot
   * @param toCol   col of to spot
   * @return
   */
  private boolean isDiagonalMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (fromRow == toRow && fromCol == toCol - 2)
            || (fromRow == toRow && fromCol == toCol + 2)
            || (fromRow == toRow - 2 && fromCol == toCol - 2)
            || (fromRow == toRow - 2 && fromCol == toCol)
            || (fromRow == toRow + 2 && fromCol == toCol)
            || (fromRow == toRow + 2 && fromCol == toCol + 2);
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          if (this.canMove(row, col, row, col - 2)
                  || this.canMove(row, col, row, col + 2)
                  || this.canMove(row, col, row - 2, col - 2)
                  || this.canMove(row, col, row - 2, col)
                  || this.canMove(row, col, row + 2, col)
                  || this.canMove(row, col, row + 2, col + 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  protected boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (withinBounds(fromRow, fromCol) && withinBounds(toRow, toCol))
            && (this.getSlotAt(fromRow, fromCol) == SlotState.Marble)
            && (this.getSlotAt(toRow, toCol) == SlotState.Empty)
            && (this.getSlotAt((fromRow + toRow) / 2,
            (fromCol + toCol) / 2) == SlotState.Marble)
            && isDiagonalMove(fromRow, fromCol, toRow, toCol);
  }

  @Override
  public int getBoardSize() {
    return armThickness;
  }

  /**
   * Checks if the move is an invalid spot.
   *
   * @param row row of the spot trying to move to
   * @param col col of the spot trying to move to
   * @return boolean - true if the spot if invalid
   */
  protected boolean invalidSpot(int row, int col) {
    return this.getBoardSize() - col + row < armThickness;
  }
}
