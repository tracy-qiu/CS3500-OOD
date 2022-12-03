package model.filters;

import model.GridPixelImage;
import model.Pixel;
import model.PixelImage;


/**
 * A function object that does different kinds of image filtering.
 */
public class MatrixFunction {

  private final PixelImage img;
  private final MyMatrix kernel;

  public MatrixFunction(PixelImage img, MyMatrix kernel) {
    this.img = img;
    this.kernel = kernel;
  }


  /**
   * Returns a new PixelImage after altered by the given kernel.
   *
   * @return new filtered image
   */
  public PixelImage filterify() {
    Pixel[][] newGrid = new Pixel[img.getHeight()][img.getWidth()];
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        newGrid[row][col] = kernel.matrixify(img, row, col);
      }
    }
    return new GridPixelImage(newGrid, img.getHeight(), img.getWidth());
  }
}
