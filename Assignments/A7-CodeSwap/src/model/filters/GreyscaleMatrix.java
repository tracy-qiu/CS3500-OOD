package model.filters;

/**
 * A greyscale transformation.
 */
public class GreyscaleMatrix extends AbstractColorTransMatrix {

  public GreyscaleMatrix() {
    super(3);
  }

  /**
   * Initializes the cell of the matrix based on the specific color transformation.
   *
   * @param matrixArray the matrix itself
   */
  @Override
  protected void specificConstructor(double[][] matrixArray) {
    for (int ii = 0; ii < 3; ii++) {
      for (int jj = 0; jj < 3; jj++) {
        if (jj == 0) {
          matrixArray[ii][jj] = 0.2126;
        } else if (jj == 1) {
          matrixArray[ii][jj] = 0.7152;
        } else {
          matrixArray[ii][jj] = 0.0722;
        }
      }
    }
  }
}
