package cs3500.marblesolitaire.model.hw02;

/**
 * This class implements MarbleSolitaireModel and represents the operations offered by the English
 * marble solitaire model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {

  private final int armThickness;
  private final int sRow;
  private final int sCol;
  private SlotState[][] boardState;

  /**
   * English solitaire model constructor that creates default board with arm thickness 3, and empty
   * cell at (3.3).
   */
  public EnglishSolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;
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
    this.armThickness = 3;
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
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;
    this.sRow = this.getBoardSize() / 2;
    this.sCol = this.getBoardSize() / 2;
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
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness " + "%d:",
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

  /**
   * This creates the English solitaire model board with arm thickness and empty cell position
   * of the model.
   *
   * @return SlotState 2D array that represents the board
   */
  private SlotState[][] initializeBoard() {
    SlotState[][] newBoard = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        int center = this.getBoardSize() / 2;
        int margin = this.armThickness / 2;
        if (row == sRow && col == sCol) {
          newBoard[row][col] = SlotState.Empty;
        } else if ((row >= center - margin && row <= center + margin)
                || (col >= center - margin && col <= center + margin)) {
          newBoard[row][col] = SlotState.Marble;
        } else {
          newBoard[row][col] = SlotState.Invalid;
        }
      }
    }
    return newBoard;
  }

  /**
   * Determines if a marbles can move from the given "from slot" to the "to slot".
   *
   * @param fromRow the row coordinate of the marble starting position
   * @param fromCol the col coordinate of the marble starting position
   * @param toRow   the row coordinate of the marble desired destination position
   * @param toCol   the col coordinate of the marble desired destination starting position
   * @return true if the marble can move to the "to slot"
   */
  private boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    return ((fromRow >= 0 && fromRow < this.getBoardSize() && fromCol >= 0
            && fromCol < this.getBoardSize())
            && (toRow >= 0 && toRow < this.getBoardSize() && toCol >= 0
            && toCol < this.getBoardSize())
            && (this.getSlotAt(fromRow, fromCol) == SlotState.Marble)
            && (this.getSlotAt(toRow, toCol) == SlotState.Empty)
            && (this.getSlotAt((fromRow + toRow) / 2,
            (fromCol + toCol) / 2) == SlotState.Marble)
            && ((fromRow == toRow - 2 && fromCol == toCol) || (fromRow == toRow + 2
            && fromCol == toCol) || (fromRow == toRow && fromCol == toCol - 2)
            || (fromRow == toRow && fromCol == toCol + 2)));
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Check valid "from" and "to" positions
    if (fromRow >= this.getBoardSize() || fromCol >= this.getBoardSize() || fromRow < 0
            || fromCol < 0 || toRow >= this.getBoardSize() || toCol >= this.getBoardSize()
            || toRow < 0 || toCol < 0) {
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
}
