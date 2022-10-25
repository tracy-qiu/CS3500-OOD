package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract Solitaire Model that controls the state of the game with moves, score, creating board.
 * Abstracts the common methods and fields between the english, european, and triangle models.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {

  protected int armThickness;
  protected int sRow;
  protected int sCol;
  protected SlotState[][] boardState;

  /**
   * Abstract Solitaire Model constructor that assigns the arm thickness, row, and col fields the
   * input parameters of the constructor.
   * @param armThickness length of the longest side of the board
   * @param sRow row of the empty spot
   * @param sCol col of the empty spot
   * @throws IllegalArgumentException if the arm thickness is less than 0
   */
  public AbstractSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness <= 0) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
  }

  /**
   * This creates the English solitaire model board with arm thickness and empty cell position
   * of the model.
   *
   * @return SlotState 2D array that represents the board
   */
  protected SlotState[][] initializeBoard() {
    SlotState[][] newBoard = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.invalidSpot(row, col)) {
          newBoard[row][col] = SlotState.Invalid;
        } else {
          newBoard[row][col] = SlotState.Marble;
        }
      }
    }
    newBoard[sRow][sCol] = SlotState.Empty;
    return newBoard;
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
    // The "to" and "from" positions are exactly two positions away (horizontally or vertically)
    else if (!((fromRow == toRow - 2 && fromCol == toCol) || (fromRow == toRow + 2
            && fromCol == toCol) || (fromCol == toCol - 2 && fromRow == toRow)
            || (fromCol == toCol + 2 && fromRow == toRow))) {
      throw new IllegalArgumentException(String.format("The 'to' and 'from' positions are not"
              + "exactly two positions away (horizontally or vertically)"));
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

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          if (this.canMove(row, col, row - 2, col)
                  || this.canMove(row, col, row - 2, col)
                  || this.canMove(row, col, row, col - 2)
                  || this.canMove(row, col, row, col + 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  protected boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (withinBounds(fromRow, fromCol) && withinBounds(toRow, toCol))
            && (toRow >= 0 && toRow < this.getBoardSize() && toCol >= 0
            && toCol < this.getBoardSize())
            && (this.getSlotAt(fromRow, fromCol) == SlotState.Marble)
            && (this.getSlotAt(toRow, toCol) == SlotState.Empty)
            && (this.getSlotAt((fromRow + toRow) / 2,
            (fromCol + toCol) / 2) == SlotState.Marble)
            && ((fromRow == toRow - 2 && fromCol == toCol) || (fromRow == toRow + 2
            && fromCol == toCol) || (fromRow == toRow && fromCol == toCol - 2)
            || (fromRow == toRow && fromCol == toCol + 2));
  }

  @Override
  public int getBoardSize() {
    return 3 * armThickness - 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row >= this.getBoardSize() || col >= this.getBoardSize() || row < 0 || col < 0) {
      throw new IllegalArgumentException(String.format("Invalid cell position " + "%d " + "%d", row,
              col));
    }
    return boardState[row][col];
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }

  protected boolean withinBounds(int row, int col) {
    return row >= 0 && row < this.getBoardSize() && col >= 0
            && col < this.getBoardSize();
  }

  protected abstract boolean invalidSpot(int row, int col);
}


