package model.filters;

/**
 * A sharpen kernel.
 */
public class SharpenMatrix extends AbstractFilterMatrix {


  public SharpenMatrix() {
    super(5);
  }


  @Override
  protected void specificConstructor(int row, int col, double[][] matrixArray) {
    if (row == 1 || row == 3 || col == 1 || col == 3) {
      matrixArray[row][col] = 0.25;
    }
    if (row == 0 || row == 4 || col == 0 || col == 4) {
      matrixArray[row][col] = -0.125;
    }
    if (row == 2 && col == 2) {
      matrixArray[row][col] = 1;
    }
  }
}
