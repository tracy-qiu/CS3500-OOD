package cs3500.marblesolitaire.model.hw02;

public class EnglishSolitaireModel implements MarbleSolitaireModel {

  private final int armThickness;
  private final int sRow;
  private final int sCol;
  SlotState[][] boardState;

  public EnglishSolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;
    this.boardState = this.initializeBoard();
  }

  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 3;
    if (sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position " + "%d " + "%d"
              , sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;
    this.boardState = this.initializeBoard();
  }

  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;
    this.sRow = armThickness;
    this.sCol = armThickness;
    this.boardState = this.initializeBoard();
  }

  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) throws IllegalArgumentException {
    if (sRow <= armThickness || sCol <= armThickness) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position " + "%d " + "%d"
              , sRow, sCol));
    }
    this.sRow = sRow;
    this.sCol = sCol;

    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid arm thickness " + "%d:",
              armThickness));
    }
    this.armThickness = armThickness;
    this.boardState = this.initializeBoard();
  }

  public SlotState[][] initializeBoard() {
    SlotState[][] newBoard = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        // center spot
        if (row == sRow && col == sCol) {
          newBoard[row][col] = SlotState.Empty;
        }
        // top left corner
        else if (row <= armThickness - 2 && col <= armThickness - 2) {
          newBoard[row][col] = SlotState.Invalid;
        }
        // top right corner
        else if (row <= armThickness - 2 && col >= 2 * armThickness - 1) {
          newBoard[row][col] = SlotState.Invalid;
        }
        // bottom left corner
        else if (row >= 2 * armThickness - 1 && col <= armThickness - 2) {
          newBoard[row][col] = SlotState.Invalid;
        }
        // bottom right corner
        else if (row >= 2 * armThickness - 1 && col >= 2 * armThickness - 1) {
          newBoard[row][col] = SlotState.Invalid;
        } else {
          newBoard[row][col] = SlotState.Marble;
        }
      }
    }
    return newBoard;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Check valid "from" and "to" positions
    if (this.getSlotAt(fromRow, fromCol) == SlotState.Invalid ||
            this.getSlotAt(toRow, toCol) == SlotState.Invalid) {
      throw new IllegalArgumentException(String.format("Invalid 'from' or 'to' position"));
    }
    // There is marble at the specified "from" position
    if (!(this.getSlotAt(fromRow, fromCol) == SlotState.Marble)) {
      throw new IllegalArgumentException(String.format("There is no marble at the specified 'from' " +
              "position"));
    }
    // The "to" position is empty
    if (!(this.getSlotAt(toRow, toCol) == SlotState.Empty)) {
      throw new IllegalArgumentException(String.format("The 'to' position is not empty"));
    }
    // The "to" and "from" positions are exactly two positions away (horizontally or vertically)
    if (!((fromRow == toRow - 2 || fromRow == toRow + 2) &&
            (fromCol == toCol - 2 || fromCol == toCol + 2))) {
      throw new IllegalArgumentException(String.format("The 'to' and 'from' positions are not" +
              "exactly two positions away (horizontally or vertically)"));
    }
    // There is a marble in the slot between the 'to' and 'from' positions
    if (!(this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2) == SlotState.Marble)) {
      throw new IllegalArgumentException(String.format("There is no marble in the slot between" +
              "the 'to' and 'from' positions"));
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
          try {
            this.move(row, col, row - 2, col);
            return false;
          } catch (IllegalArgumentException e) {
          }
          try {
            this.move(row, col, row + 2, col);
            return false;
          } catch (IllegalArgumentException e) {
          }
          try {
            this.move(row, col, row, col - 2);
            return false;
          } catch (IllegalArgumentException e) {
          }
          try {
            this.move(row, col, row, col + 2);
            return false;
          } catch (IllegalArgumentException e) {
          }
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    int boardSize = 3 * armThickness - 2;
    return boardSize;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row > this.getBoardSize() || col > this.getBoardSize() || row < 0 || col < 0) {
      throw new IllegalArgumentException(String.format("Invalid cell position " + "%d " + "%d"
              , row, col));
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
