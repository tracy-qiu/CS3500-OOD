package controller;

import java.io.File;
import java.io.IOException;

import model.ImageProcessor;
import model.PixelImage;


/**
 * Mock model to confirm whether the controller communicates properly with the model.
 */
public class ConfirmInputsModel implements ImageProcessor {

  Appendable log;


  /**
   * Constructs a confirm inputs model.
   *
   * @param log log
   */
  public ConfirmInputsModel(Appendable log) {
    this.log = log;
  }


  /**
   * Loads the image at the given path and refers it by the given image name.
   *
   * @param file    the file
   * @param imgName the name of the image.
   */
  @Override
  public void loadImage(File file, String imgName) throws IOException {
    try {
      log.append(String.format("Command: load %s %s\n", file.getName(), imgName));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

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
    try {
      log.append(String.format("Command: extract-component %s %s %s\n", imgName, destName, color));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
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
    try {
      log.append(String.format("Command: greyscale %s %s %s\n", imgName, destName, type));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
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
    try {
      if (direction.equals("vertical")) {
        log.append(String.format("Command: flip-vertical %s %s\n", imgName, destName));
      } else {
        log.append(String.format("Command: flip-horizontal %s %s\n", imgName, destName));
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
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
    try {
      if (factor < 0) {
        log.append(String.format("Command: darken %s %s %d\n", imgName, destName, -factor));
      } else {
        log.append(String.format("Command: brighten %s %s %d\n", imgName, destName, factor));
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }


  /**
   * Return a map of the images loaded so for with their names.
   *
   * @param imgName the img name
   * @return a img with the given name
   */
  @Override
  public PixelImage getLoadedImg(String imgName) {
    try {
      log.append(String.format("Command: get image %s\n", imgName));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return null;
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
    try {
      log.append(String.format("Command: filter %s %s %s\n", imgName, destName, type));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Ensures that the given key is a valid image name.
   *
   * @param key the key
   */
  @Override
  public void ensureKey(String key) {
    try {
      log.append(String.format("Command: ensure %s\n", key));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public void mosaicImage(String imgName, String destName, int numSeeds) {
    try {
      log.append(String.format("Command: mosaic %s %s %d\n", imgName, destName, numSeeds));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
