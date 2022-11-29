package model;

/**
 * Class represents the flip image functionality (function object) that is applied to an image
 * using run.
 */
public class FlipImageProcessCommand implements IImageProcessCommand {
  private final boolean horizontal;

  /**
   * constructor for the FlipImage function object. Takes in boolean to determine type of flip.
   *
   * @param horizontal flip type.
   */
  public FlipImageProcessCommand(boolean horizontal) {
    this.horizontal = horizontal;
  }

  /**
   * run method that applies the flip functionality to an image to produce new changed image.
   *
   * @param image image being altered with particular function object (flip).
   * @return image that has been flipped after function object is applied.
   */
  @Override
  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    Pixel[][] flippedImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        if (horizontal) {
          flippedImage[row][col] = image.getPixelAt(row, image.getWidth() - col - 1);
        } else {
          flippedImage[row][col] = image.getPixelAt(image.getHeight() - row - 1, col);
        }
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), flippedImage);
  }
}
