package model;

/**
 * Interface that represents applying functionality changes that edit the images based on specific
 * methods.
 */
public interface IImageProcessCommand {

  /**
   * Method that takes in an image, and applies a particular function object to an existing image
   * and produces a new image with these functionality changes.
   * @param image image being altered with particular function object.
   * @return new image that has been altered by function object.
   * @throws IllegalArgumentException when Image is null;
   */
  ImageImpl run(ImageImpl image) throws IllegalArgumentException;
}
