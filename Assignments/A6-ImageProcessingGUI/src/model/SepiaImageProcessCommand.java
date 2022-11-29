package model;

/**
 * Class that represents the Sepia functionality stored as a function object.
 */
public class SepiaImageProcessCommand implements IImageProcessCommand {


  /**
   * run method that applies the Sepia functionality to an image to produce new changed image.
   *
   * @param image image being altered with particular function object.
   * @return Image that has had sepia functionality applied to it.
   * @throws IllegalArgumentException if the image is null.
   */
  @Override
  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    Pixel[][] sepiaImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        sepiaImage[row][col] = image.getPixelAt(row, col).sepia();
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), sepiaImage);
  }
}
