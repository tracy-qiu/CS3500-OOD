package model;

public class BlurImageProcessCommand extends AbstractFilterCommands {

  public BlurImageProcessCommand() {
    super(new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625} });
    //super({{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625} });

  }

//  @Override
//  public ImageImpl run(ImageImpl image) throws IllegalArgumentException {
//    if (image == null) {
//      throw new IllegalArgumentException("Null image");
//    }
//    Pixel[][] blurredImage = new Pixel[image.getHeight()][image.getWidth()];
//    int[][] redChannelPixels = this.channelPixels("red", image);
//    int[][] greenChannelPixels = this.channelPixels("green", image);
//    int[][] blueChannelPixels = this.channelPixels("blue", image);
//
//    for (int row = 0; row < image.getHeight(); row++) {
//      for (int col = 0; col < image.getWidth(); col++) {
//            blurredImage[row][col] = new Pixel(blurPixel(row, col, redChannelPixels), blurPixel(row, col, greenChannelPixels), blurPixel(row, col, blueChannelPixels), image.getMaxValue());
//        }
//      }
//    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), blurredImage);
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

  protected int filter(int row, int col, int[][] channelPixels){
    double[][] blurFilter = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625} };
    double total = 0;
    for (int blurRow = row - this.borderKernal; blurRow <= row + this.borderKernal; blurRow++){
      for (int blurCol = col - this.borderKernal; blurCol <= col + this.borderKernal; blurCol++){
        if (!(blurRow < 0 || blurRow > channelPixels.length - 1 || blurCol < 0 ||
                blurCol > channelPixels[0].length - 1)){
          total += this.kernal[blurRow - row + this.borderKernal][blurCol - col + this.borderKernal] * channelPixels[blurRow][blurCol];
        }
      }
    }
    System.out.println(total);
    return (int)total;

  }
}
