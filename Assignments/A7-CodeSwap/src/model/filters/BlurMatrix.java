package model.filters;


/**
 * A blur kernel.
 */
public class BlurMatrix extends AbstractFilterMatrix {

  public BlurMatrix() {
    super(3);
  }


  @Override
  protected void specificConstructor(int row, int col, double[][] matrixArray) {
    if (row == 0 || row == 2) {
      matrixArray[row][col] = 0.0625;
    }
    if (row == 1 || col == 1) {
      matrixArray[row][col] = 0.125;
    }
    if (row == 1 && col == 1) {
      matrixArray[row][col] = 0.25;
    }
  }
}