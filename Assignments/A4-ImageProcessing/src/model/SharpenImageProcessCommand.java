package model;

public class SharpenImageProcessCommand extends AbstractFilterCommands {
  //private final String channel;

  public SharpenImageProcessCommand() {
    super(new double[][] {{-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}});
    //this.channel = channel;
  }

//  @Override
//  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
//    if (image == null) {
//      throw new IllegalArgumentException("Null image");
//    }
//    Pixel[][] sharpenedImage = new Pixel[image.getHeight()][image.getWidth()];
//    int[][] redChannelPixels = this.channelPixels("red", image);
//    int[][] greenChannelPixels = this.channelPixels("green", image);
//    int[][] blueChannelPixels = this.channelPixels("blue", image);
//
//    for (int row = 0; row < image.getHeight(); row++) {
//      for (int col = 0; col < image.getWidth(); col++) {
//            sharpenedImage[row][col] = new Pixel(sharpenPixel(row, col, redChannelPixels), sharpenPixel(row, col, greenChannelPixels), sharpenPixel(row, col, blueChannelPixels), image.getMaxValue());
//      }
//    }
//    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), sharpenedImage);
//  }
//
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

  @Override
  protected int filter(int row, int col, int[][] channelPixels) {
    double[][] sharpenFilter =
            {{-0.125, -0.125, -0.125, -0.125, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, 0.25, 1, 0.25, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, -0.125, -0.125, -0.125, -0.125}};

    // size of kernal - 1 divided by two
    double total = 0;
    for (int sharpenRow = row - this.borderKernal; sharpenRow <= row + this.borderKernal; sharpenRow++) {
      for (int sharpenCol = col - this.borderKernal; sharpenCol <= col + this.borderKernal; sharpenCol++) {
        if (!(sharpenRow < 0 || sharpenRow > channelPixels.length - 1 || sharpenCol < 0 ||
                sharpenCol > channelPixels[0].length - 1)) {
          total += this.kernal[sharpenRow - row + this.borderKernal][sharpenCol - col + this.borderKernal] * channelPixels[sharpenRow][sharpenCol];
        }
      }
    }
    System.out.println(total);
    return (int) total;
  }
}
