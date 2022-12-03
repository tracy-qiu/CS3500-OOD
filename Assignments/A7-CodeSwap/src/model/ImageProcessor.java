package model;

import java.io.File;
import java.io.IOException;

/**
 * Representing the operations and observations that preserve the original PixelImage.
 */
public interface ImageProcessor {


  /**
   * Loads the image at the given path and refers it by the given image name.
   *
   * @param file    the file
   * @param imgName the name of the image.
   */
  void loadImage(File file, String imgName) throws IOException;


  /**
   * Extracts the given component from the image.
   *
   * @param imgName  the image name
   * @param destName name of the new image
   * @param color    color to extract.
   */
  public void extractComponent(String imgName, String destName, String color);


  /**
   * Visualizes the given image in greyscale according to the specified type.
   *
   * @param imgName  the image to visualize
   * @param destName the new image to save
   * @param type     the type of greyscale - value, intensity or luma
   */
  void visGreyscale(String imgName, String destName, String type);


  /**
   * Visualizes the image as the mirror image in the given direction of the original.
   *
   * @param imgName   the image to flip.
   * @param destName  the new image to save
   * @param direction the direction to flip
   */
  void flipImage(String imgName, String destName, String direction);


  /**
   * Creates a new image as a brighter or darker version of the original image by a certain factor.
   *
   * @param imgName  the image to brighten
   * @param factor   the factor to brighten
   * @param destName the new image to save
   */
  void adjustBrightness(String imgName, int factor, String destName);


  /**
   * Return a map of the images loaded so for with their names.
   *
   * @param imgName the img name
   * @return an img with the given name
   */
  PixelImage getLoadedImg(String imgName);

  /**
   * Creates a new image based on the filter applied to the given image.
   *
   * @param imgName  the img to filter
   * @param destName the filtered img
   * @param type     the type of filter - blur and sharpen
   */
  void filterImage(String imgName, String destName, String type);


  /**
   * Ensures that the given key is a valid image name.
   *
   * @param key the key
   */
  void ensureKey(String key);

  void mosaicImage(String imgName, String destName, int numSeeds);
}
