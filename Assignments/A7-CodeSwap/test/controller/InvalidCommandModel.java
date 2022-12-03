package controller;

import java.io.File;

import model.ImageProcessor;
import model.PixelImage;


/**
 * A mock model that always throws an IllegalArgException.
 */
public class InvalidCommandModel implements ImageProcessor {
  /**
   * Loads the image at the given path and refers it by the given image name.
   *
   * @param file    the file
   * @param imgName the name of the image.
   */
  @Override
  public void loadImage(File file, String imgName) {
    throw new IllegalArgumentException("get flounced");
  }

  /**
   * Extracts the given component from the image.
   *
   * @param imgName  the image name
   * @param destName name of the new image
   * @param color    color to extract.
   */
  @Override
  public void extractComponent(String imgName, String destName, String color) {
    throw new IllegalArgumentException("get flounced");
  }


  /**
   * Visualizes the given image in greyscale according to the specified type.
   *
   * @param imgName  the image to visualize
   * @param destName the new image to save
   * @param type     the type of greyscale - value, intensity or luma
   */
  @Override
  public void visGreyscale(String imgName, String destName, String type) {
    throw new IllegalArgumentException("get flounced");

  }

  /**
   * Visualizes the image as the mirror image in the given direction of the original.
   *
   * @param imgName   the image to flip.
   * @param destName  the new image to save
   * @param direction the direction to flip
   */
  @Override
  public void flipImage(String imgName, String destName, String direction) {
    throw new IllegalArgumentException("get flounced");

  }

  /**
   * Creates a new image as a brighter or darker version of the original image by a certain factor.
   *
   * @param imgName  the image to brighten
   * @param factor   the factor to brighten
   * @param destName the new image to save
   */
  @Override
  public void adjustBrightness(String imgName, int factor, String destName) {
    throw new IllegalArgumentException("get flounced");

  }

  /**
   * Return a map of the images loaded so for with their names.
   *
   * @param imgName the img name
   * @return a img with the given name
   */
  @Override
  public PixelImage getLoadedImg(String imgName) {
    throw new IllegalArgumentException("get flounced");

  }

  /**
   * Creates a new image based on the filter applied to the given image.
   *
   * @param imgName  the img to filter
   * @param destName the filtered img
   * @param type     the type of filter - blur and sharpen
   */
  @Override
  public void filterImage(String imgName, String destName, String type) {
    throw new IllegalArgumentException("get flounced");
  }

  /**
   * Ensures that the given key is a valid image name.
   *
   * @param key the key
   */
  @Override
  public void ensureKey(String key) {
    throw new IllegalArgumentException("get flounced");
  }

  @Override
  public void mosaicImage(String imgName, String destName, int numSeeds) {
    throw new IllegalArgumentException("Invalid mosaic command");
  }
}
