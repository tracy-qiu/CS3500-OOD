package model.filters;

import model.Pixel;
import model.PixelImage;
import model.StdPixel;

/**
 * The abstract class for the implementation of the filtering matrix.
 */
public abstract class AbstractFilterMatrix implements MyMatrix {

  private final double[][] matrixArray;

  /**
   * Constructs the filter matrix with the given size.
   *
   * @param size the size of the matrix
   */
  protected AbstractFilterMatrix(int size) {
    this.matrixArray = new double[size][size];

    for (int ii = 0; ii < size; ii++) {
      for (int jj = 0; jj < size; jj++) {
        this.specificConstructor(ii, jj, this.matrixArray);
      }
    }
  }

  /**
   * Initializes the cell of the matrix based on the specific filter.
   *
   * @param row         the row of the matrix
   * @param col         the column of the matrix
   * @param matrixArray the matrix itself
   */
  protected abstract void specificConstructor(int row, int col, double[][] matrixArray);

  /**
   * Returns the pixel after being altered by this matrix.
   *
   * @param img the og img
   * @param row the row of the pixel
   * @param col the col of the pixel
   * @return the new pixel
   */
  @Override
  public Pixel matrixify(PixelImage img, int row, int col) {
    Pixel currPxl = img.getPixelAt(row, col);
    double newRed = 0;
    double newGreen = 0;
    double newBlue = 0;
    int offset = ((this.matrixArray.length - 1) / 2);

    for (int mRow = 0; mRow < this.matrixArray.length; mRow++) {
      for (int mCol = 0; mCol < this.matrixArray.length; mCol++) {
        if (img.isValidPxl(row + mRow - offset, col + mCol - offset)) {
          newRed += this.matrixArray[mRow][mCol] *
                  img.getPixelAt(row + mRow - offset, col + mCol - offset).getRed();
          newGreen += this.matrixArray[mRow][mCol] *
                  img.getPixelAt(row + mRow - offset, col + mCol - offset).getGreen();
          newBlue += this.matrixArray[mRow][mCol] *
                  img.getPixelAt(row + mRow - offset, col + mCol - offset).getBlue();
        }
      }
    }
    return new StdPixel(currPxl.normalize((int) Math.round(newRed)),
            currPxl.normalize((int) Math.round(newGreen)),
            currPxl.normalize((int) Math.round(newBlue)),
            currPxl.getMaxVal());
  }
}
