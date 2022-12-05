package model;


import java.util.Random;

/**
 * Interface representing the operations and observations on an image of pixels.
 */
public interface PixelImage {

  /**
   * Returns the value of the pixel at the given row and column.
   *
   * @param row the row value of the pixel
   * @param col the column value of the pixel
   * @return the Pixel at the given row and column values
   */
  Pixel getPixelAt(int row, int col);

  /**
   * Returns the number of rows of pixels in this image.
   *
   * @return the height
   */
  int getHeight();


  /**
   * Returns the number of columns of pixels in this image.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Returns the maximum numeric value a pixel in this PixelImage can have.
   *
   * @return an integer representing the max value.
   */
  int getMaxValue();

  /**
   * Visualizes the image as a color-scaled image.
   *
   * @param color the color to grayscale
   * @return a new PixelImage in blue scale.
   */
  PixelImage getComponent(String color);


  /**
   * Visualizes the given image in greyscale according to the specified type.
   *
   * @param type the type of greyscale - value, intensity or luma
   * @return a new Image greyscaled accordingly.
   */
  PixelImage visGreyscale(String type);

  /**
   * Visualizes this image as the mirror image in the given direction of the original.
   *
   * @param direction the direction to flip.
   * @return a new Image flipped horizontally.
   */
  PixelImage flipImage(String direction);

  /**
   * Visualizes the image as a brighter/darker version of
   * the original image by a certain factor.
   *
   * @param factor the factor to brighten
   * @return a new PixelImage that is brightened.
   */
  PixelImage adjustBrightness(int factor);

  PixelImage mosaic(int numSeeds);

  PixelImage mosaicRandomTesting(int numSeeds, int random);

  /**
   * Saves this image to the given path.
   *
   * @param path the path to save to.
   */
  void saveImg(String path);

  /**
   * Filters this image according to the type.
   *
   * @param type the type of filter
   * @return a new pixel image that is filtered
   */
  PixelImage filter(String type);

  /**
   * Is the given posn within the valid boundaries of the image.
   *
   * @param row row
   * @param col col
   * @return true if valid.
   */
  boolean isValidPxl(int row, int col);


  /**
   * Computes the frequency of rgb values and the intensity of each pixel.
   *
   * @return an array of 4 maxval + 1 sized arrays representing rgb and intensity
   */
  int[][] computeDistr();
}
