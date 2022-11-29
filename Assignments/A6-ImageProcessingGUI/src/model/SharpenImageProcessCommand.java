package model;

/**
 * Class that represents the Blur functionality stored as a function object.
 */
public class SharpenImageProcessCommand extends AbstractFilterCommands {
  //private final String channel;

  /**
   * constructor for the Sharpen image process command. Inherits from abstract class and initializes
   * its own kernel.
   */
  public SharpenImageProcessCommand() {
    super(new double[][] {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}});
    //this.channel = channel;
  }

  /**
   * method that applies sharpen functionality to a Pixel in an image.
   * @param row of specific pixel.
   * @param col of specific pixel.
   * @param channelPixels 2D array of pixel which functionality is applied.
   * @return integer that represents new sharpened color component of pixel.
   */
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
    for (int sharpenRow = row - this.borderKernel; sharpenRow <= row + this.borderKernel;
         sharpenRow++) {
      for (int sharpenCol = col - this.borderKernel; sharpenCol <= col + this.borderKernel;
           sharpenCol++) {
        if (!(sharpenRow < 0 || sharpenRow > channelPixels.length - 1 || sharpenCol < 0
                || sharpenCol > channelPixels[0].length - 1)) {
          total += this.kernel[sharpenRow - row + this.borderKernel][sharpenCol - col
                  + this.borderKernel] * channelPixels[sharpenRow][sharpenCol];
        }
      }
    }
    return (int) total;
  }
}
