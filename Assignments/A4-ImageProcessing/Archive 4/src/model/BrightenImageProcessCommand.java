package model;

/**
 * Class that represents the Brighten functionality stored as a functional object.
 */
public class BrightenImageProcessCommand implements IImageProcessCommand {
  private final int amount;

  /**
   * constructor for brighten function object. Takes in amount that image will be brighten or
   * dimmed by.
   *
   * @param amount amount that image colors are changed by.
   */
  public BrightenImageProcessCommand(int amount) {
    this.amount = amount;
  }

  /**
   * run method that applies the Brighten functionality to an image to produce new changed image.
   *
   * @param image image being altered with particular function object.
   * @return new image brightened by functionality.
   */
  @Override
  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    Pixel[][] brightenedImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        brightenedImage[row][col] = image.getPixelAt(row, col).brighten(amount);
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), brightenedImage);
  }
}
