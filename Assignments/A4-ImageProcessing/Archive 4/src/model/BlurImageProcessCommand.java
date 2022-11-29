package model;

public class BlurImageProcessCommand implements IImageProcessCommand {

  private final String channel;

  public BlurImageProcessCommand(String channel) {
    this.channel = channel;
  }

  @Override
  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    Pixel[][] blurredImage = new Pixel[image.getHeight()][image.getWidth()];
    int[][] channelPixels = this.channelPixels(channel, image);

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        switch (channel){
          case "red":
            blurredImage[row][col] = new Pixel(blurPixel(row, col, channelPixels), image.getPixelAt(row, col).getGreen(), image.getPixelAt(row, col).getBlue(), image.getMaxValue());
          case "green":
            blurredImage[row][col] = new Pixel(image.getPixelAt(row, col).getRed(), blurPixel(row, col, channelPixels), image.getPixelAt(row, col).getBlue(), image.getMaxValue());
          case "blue":
            blurredImage[row][col] = new Pixel(image.getPixelAt(row, col).getRed(), image.getPixelAt(row, col).getGreen(), blurPixel(row, col, channelPixels), image.getMaxValue());
          default:

        }
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), blurredImage);
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

  private int blurPixel(int row, int col, int[][] channelPixels){
    double[][] blurFilter = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625} };
    double total = 0;
    for (int blurRow = -1; blurRow < 2; blurRow++){
      for (int blurCol = -1; blurCol < 2; blurCol++){
        if (!(row + blurRow < 0 || row + blurRow > channelPixels.length || col + blurCol < 0 ||
                col + blurCol > channelPixels[0].length)){
          total += blurFilter[blurRow][blurCol] * channelPixels[row + blurRow][col + blurCol];
        }
      }
    }
    return (int)total;
  }
}
