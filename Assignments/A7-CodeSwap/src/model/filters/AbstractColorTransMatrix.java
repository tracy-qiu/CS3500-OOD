package model.filters;

import model.Pixel;
import model.PixelImage;
import model.StdPixel;

/**
 * The abstract class for the implementation of the color transformation matrix.
 */
public abstract class AbstractColorTransMatrix implements MyMatrix {

  private final double[][] matrixArray;

  public AbstractColorTransMatrix(int size) {
    this.matrixArray = new double[size][size];
    this.specificConstructor(this.matrixArray);
  }

  /**
   * Initializes the cell of the matrix based on the specific color transformation.
   *
   * @param matrixArray the matrix itself
   */
  protected abstract void specificConstructor(double[][] matrixArray);


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
    int newRed = (int) Math.round(this.matrixArray[0][0] * currPxl.getRed() +
            this.matrixArray[0][1] * currPxl.getGreen() +
            this.matrixArray[0][2] * currPxl.getBlue());
    int newGreen = (int) Math.round(this.matrixArray[1][0] * currPxl.getRed() +
            this.matrixArray[1][1] * currPxl.getGreen() +
            this.matrixArray[1][2] * currPxl.getBlue());
    int newBlue = (int) Math.round(this.matrixArray[2][0] * currPxl.getRed() +
            this.matrixArray[2][1] * currPxl.getGreen() +
            this.matrixArray[2][2] * currPxl.getBlue());

    return new StdPixel(currPxl.normalize(newRed), currPxl.normalize(newGreen),
            currPxl.normalize(newBlue), currPxl.getMaxVal());
  }
}
