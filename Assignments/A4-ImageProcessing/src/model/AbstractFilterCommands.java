package model;

abstract public class AbstractFilterCommands implements IImageProcessCommand {

  protected final double[][] kernal;
  protected final int borderKernal;

  public AbstractFilterCommands(double[][] kernal) {
    this.kernal = kernal;
    this.borderKernal = ((kernal.length - 1) /2) ;

  }

  @Override
  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Null image");
    }
    Pixel[][] filteredImage = new Pixel[image.getHeight()][image.getWidth()];
    int[][] redChannelPixels = image.channelPixels("red");
    int[][] greenChannelPixels = image.channelPixels("green");
    int[][] blueChannelPixels = image.channelPixels("blue");

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        filteredImage[row][col] = new Pixel(filter(row, col, redChannelPixels), filter(row, col, greenChannelPixels), filter(row, col, blueChannelPixels), image.getMaxValue());
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), filteredImage);
  }

//  private int[][] channelPixels(String channel, ImageImpl image) {
//    int[][] imageChannel = new int[image.getWidth()][image.getHeight()];
//
//    for (int row = 0; row < image.getHeight(); row++) {
//      for (int col = 0; col < image.getWidth(); col++) {
//        switch (channel) {
//          case "red":
//            imageChannel[row][col] = image.getPixelAt(row, col).getRed();
//            break;
//          case "green":
//            imageChannel[row][col] = image.getPixelAt(row, col).getGreen();
//            break;
//          case "blue":
//            imageChannel[row][col] = image.getPixelAt(row, col).getBlue();
//            break;
//          default:
//            throw new IllegalArgumentException("Invalid channel color");
//        }
//      }
//    }
//    return imageChannel;
//  }

  protected abstract int filter(int row, int col, int[][] channelPixels);

}
