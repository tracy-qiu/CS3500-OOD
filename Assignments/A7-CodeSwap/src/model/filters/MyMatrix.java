package model.filters;


import model.Pixel;
import model.PixelImage;

/**
 * Represents a color Matrix.
 */
public interface MyMatrix {


  /**
   * Returns the pixel after being altered by this matrix.
   *
   * @param img the og img
   * @param row the row of the pixel
   * @param col the col of the pixel
   * @return the new pixel
   */
  Pixel matrixify(PixelImage img, int row, int col);
}
