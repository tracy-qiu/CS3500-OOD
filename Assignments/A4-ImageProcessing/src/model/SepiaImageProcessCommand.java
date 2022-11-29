package model;

public class SepiaImageProcessCommand implements IImageProcessCommand {
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
