package model;

/**
 * Interface represents the functionality used to get the state of the image representation. Used
 * to monitor the state of the Image implementation.
 */
public interface IImage {

  /**
   * method used to get the total pixels of an image.
   *
   * @return integer that represents total pixels.
   */
  int totalPixels();

  /**
   * method used to get the width of an image.
   *
   * @return integer that represents the width of image.
   */
  int getWidth();

  /**
   * method used to get the height of an image.
   *
   * @return integer that represents the height of an image.
   */
  int getHeight();

  /**
   * method used to get the maxValue of an image.
   *
   * @return
   */
  int getMaxValue();

  /**
   * method used to get the Pixel at a particular place in the image.
   *
   * @param row row of the pixel in image.
   * @param col col of pixel in image.
   * @return pixel at the particular spot in image.
   */
  Pixel getPixelAt(int row, int col);

  /**
   * method used to convert the ImageImpl into a String that corresponds with ppm file format.
   *
   * @return String that represents ppm of the ImageImpl.
   */
  String ppmOutput();

//  int[][] makeHistogram();
}
