package spreadsheet;

/**
 * This class implements the extended spreadsheet.
 */
public class SpreadsheetExtendImpl implements SpreadsheetExtend {
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    this.set(row, col, value);
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return false;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public void execute(Macro macro) {
    macro.execute(this);
  }
}
