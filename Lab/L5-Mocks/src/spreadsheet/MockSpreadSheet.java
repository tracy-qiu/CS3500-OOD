package spreadsheet;

import java.io.IOException;

/**
 * This class defines the a mock spreadsheet that implements a spreadsheet.
 * It takes in a Spreadsheet object and an Appendable output.
 */
public class MockSpreadSheet implements SpreadSheet {
  private final Appendable output;

  private SpreadSheet spreadSheet;

  /**
   * MockSpreadSheet constructor that takes non null spreadsheet object and appendable output.
   * @param spreadSheet Spreadsheet object
   * @param output Appendable output that transmits output message
   * @throws IllegalArgumentException if output or spreadsheet are null
   */
  public MockSpreadSheet(SpreadSheet spreadSheet, Appendable output) throws
          IllegalArgumentException {
    if (output == null) {
      throw new IllegalArgumentException(String.format("Null mock spread sheet appendable"));
    }
    this.output = output;
    if (spreadSheet == null) {
      throw new IllegalArgumentException(String.format("Null mock model appendable"));
    }
    this.spreadSheet = spreadSheet;

  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return this.spreadSheet.get(row, col);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    this.spreadSheet.set(row, col, value);
    try {
      this.output.append(String.format("%d was set to (%d, %d) in the spreadsheet", value, row,
              col));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return this.spreadSheet.isEmpty(row, col);
  }

  @Override
  public int getWidth() {
    return this.spreadSheet.getWidth();
  }

  @Override
  public int getHeight() {
    return this.spreadSheet.getHeight();
  }
}
