package spreadsheet;

/**
 * This class implements a macro that can be used to assign a range of spots of a spreadsheet
 * to a certain value.
 */
public class MacroImpl implements Macro {
  int minRow;
  int minCol;
  int maxRow;
  int maxCol;
  int value;


  /**
   * This is the MacroImpl constructor.
   * @param minRow int minimum row
   * @param minCol int minimum column
   * @param maxRow int maximum row
   * @param maxCol int maximum column
   * @param value int value assigned to range of spots
   */
  public MacroImpl(int minRow, int minCol, int maxRow, int maxCol, int value) {
    if (this.minRow < 0 || this.minCol < 0) {
      throw new IllegalArgumentException(String.format("Invalid range of cells"));
    }
    this.minRow = minRow;
    this.minCol = minCol;
    this.maxRow = maxRow;
    this.maxCol = maxCol;
    this.value = value;

  }

  @Override
  public void execute(SpreadSheet spreadsheet) {
    for (int row = minRow; row <= maxRow; row++) {
      for (int col = minCol; col <= maxCol; col++) {
        spreadsheet.set(row, col, value);
      }
    }
  }
}
