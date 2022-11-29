package model;

/**
 * This interface represents the operations that can be applied to a pixel as well as functionality
 * used to observe the state of a pixel.
 */
public interface IPixel {

  /**
   * Returns the red component of a pixel.
   *
   * @return integer that represents the red component of a pixel.
   */
  int getRed();

  /**
   * Returns the green component of a pixel.
   *
   * @return integer that represents the green component of a pixel.
   */
  int getGreen();

  /**
   * Returns the blue component of a pixel.
   *
   * @return integer that represents the blue component of a pixel.
   */
  int getBlue();
}
