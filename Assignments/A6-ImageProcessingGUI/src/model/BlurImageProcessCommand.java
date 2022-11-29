package model;

/**
 * Class that represents the Blur functionality stored as a function object.
 */
public class BlurImageProcessCommand extends AbstractFilterCommands {

  /**
   * constructor for the Blur image process command. Inherits from abstract class and initializes
   * its own kernel.
   */
  public BlurImageProcessCommand() {
    super(new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}});
  }

  /**
   * method that applies blur functionality to a Pixel in an image.
   *
   * @param row           of specific pixel.
   * @param col           of specific pixel.
   * @param channelPixels 2D array of pixel which functionality is applied.
   * @return integer that represents new blurred color component of pixel.
   */
  protected int filter(int row, int col, int[][] channelPixels) {
    double total = 0;
    for (int blurRow = row - this.borderKernel; blurRow <= row + this.borderKernel; blurRow++) {
      for (int blurCol = col - this.borderKernel; blurCol <= col + this.borderKernel; blurCol++) {
        if (!(blurRow < 0 || blurRow > channelPixels.length - 1 || blurCol < 0
                || blurCol > channelPixels[0].length - 1)) {
          total += this.kernel[blurRow - row + this.borderKernel]
                  [blurCol - col + this.borderKernel] * channelPixels[blurRow][blurCol];
        }
      }
    }
    // System.out.println(total);
    return (int) total;

  }
}
