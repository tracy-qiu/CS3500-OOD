package model;

public class SharpenImageProcessCommand implements IImageProcessCommand {
  private final String channel;

  public SharpenImageProcessCommand(String channel) {
    this.channel = channel;
  }

  @Override
  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    Pixel[][] sharpenedImage = new Pixel[image.getHeight()][image.getWidth()];
    int[][] channelPixels = this.channelPixels(channel, image);

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        switch (channel) {
          case "red":
            sharpenedImage[row][col] = new Pixel(sharpenPixel(row, col, channelPixels), image.getPixelAt(row, col).getGreen(), image.getPixelAt(row, col).getBlue(), image.getMaxValue());
          case "green":
            sharpenedImage[row][col] = new Pixel(image.getPixelAt(row, col).getRed(), sharpenPixel(row, col, channelPixels), image.getPixelAt(row, col).getBlue(), image.getMaxValue());
          case "blue":
            sharpenedImage[row][col] = new Pixel(image.getPixelAt(row, col).getRed(), image.getPixelAt(row, col).getGreen(), sharpenPixel(row, col, channelPixels), image.getMaxValue());
          default:

        }
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), sharpenedImage);
  }

  private int[][] channelPixels(String channel, ImageImpl image) {
    int[][] imageChannel = new int[image.getWidth()][image.getHeight()];

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        switch (channel) {
          case "red":
            imageChannel[row][col] = image.getPixelAt(row, col).getRed();
            break;
          case "green":
            imageChannel[row][col] = image.getPixelAt(row, col).getGreen();
            break;
          case "blue":
            imageChannel[row][col] = image.getPixelAt(row, col).getBlue();
            break;
          default:
            throw new IllegalArgumentException("Invalid channel color");
        }
      }
    }
    return imageChannel;
  }

  private int sharpenPixel(int row, int col, int[][] channelPixels) {
    double[][] sharpenFilter =
            {{-0.125, -0.125, -0.125, -0.125, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, 0.25, 1, 0.25, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, -0.125, -0.125, -0.125, -0.125}};
    double total = 0;
    for (int sharpenRow = -2; sharpenRow < 3; sharpenRow++) {
      for (int sharpenCol = -2; sharpenCol < 3; sharpenCol++) {
        if (!(row + sharpenRow < 0 || row + sharpenRow >= channelPixels.length || col + sharpenCol < 0 ||
                col + sharpenCol >= channelPixels[0].length)) {
          total += sharpenFilter[sharpenRow][sharpenCol] * channelPixels[row + sharpenRow][col + sharpenCol];
        }
      }
    }
    return (int) total;
  }
}
