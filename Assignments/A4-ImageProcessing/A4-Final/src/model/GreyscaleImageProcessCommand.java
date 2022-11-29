package model;

/**
 * Class represents function object for the functionality of Greyscale method, which is applied
 * to an image.
 */
public class GreyscaleImageProcessCommand implements IImageProcessCommand {

  private final String componentType;

  /**
   * constructor for GreyscaleImageProcessCommand, takes in a string that indicates the type
   * of Greyscale function that will be applied to the image.
   *
   * @param componentType type of Greyscale function that will be applied.
   */
  public GreyscaleImageProcessCommand(String componentType) {
    this.componentType = componentType;
  }


  /**
   * run method for the Greyscale function object. Applies particular Greyscale function to image
   * and produces a new image with the changes.
   *
   * @param image image being altered with particular function object.
   * @return new Image with the Greyscale changed applied.
   */
  @Override
  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    Pixel[][] greyscaleImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        greyscaleImage[row][col] = image.getPixelAt(row, col).greyscale(componentType);
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), greyscaleImage);
  }
}
