package model.filters;

/**
 * A sepia transformation matrix.
 */
public class SepiaMatrix extends AbstractColorTransMatrix {

  public SepiaMatrix() {
    super(3);
  }


  /**
   * Initializes the cell of the matrix based on the specific color transformation.
   *
   * @param matrixArray the matrix itself
   */
  @Override
  protected void specificConstructor(double[][] matrixArray) {
    matrixArray[0][0] = 0.393;
    matrixArray[0][1] = 0.769;
    matrixArray[0][2] = 0.189;
    matrixArray[1][0] = 0.349;
    matrixArray[1][1] = 0.686;
    matrixArray[1][2] = 0.168;
    matrixArray[2][0] = 0.272;
    matrixArray[2][1] = 0.534;
    matrixArray[2][2] = 0.131;
  }
}