package model;

/**
 * Abstract Process Command class used for process commands that implement filtering functionality.
 */
public abstract class AbstractFilterCommands implements IImageProcessCommand {

  protected final double[][] kernel;
  protected final int borderKernel;


  /**
   * constructor of the Abstract Filter Command class.
   *
   * @param kernel represents kernel with specific filtering values for particular functionality.
   */
  public AbstractFilterCommands(double[][] kernel) {
    this.kernel = kernel;
    this.borderKernel = ((kernel.length - 1) / 2);

  }

  /**
   * method that applies the particular filter function object to an image to produce new changed
   * image.
   *
   * @param image image being altered with particular function object.
   * @return new Image that has been altered with desired functionality.
   * @throws IllegalArgumentException if the image is null.
   */
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
        filteredImage[row][col] = new Pixel(filter(row, col, redChannelPixels),
                filter(row, col, greenChannelPixels),
                filter(row, col, blueChannelPixels), image.getMaxValue());
      }
    }
    return new ImageImpl(image.getWidth(), image.getHeight(), image.getMaxValue(), filteredImage);
  }

  /**
   * filter method that applies specific kernel to image depending on desired functionality.
   *
   * @param row           of specific pixel.
   * @param col           of specific pixel.
   * @param channelPixels 2D array of pixel which functionality is applied
   * @return integer that represents new value of color component of a pixel
   */
  protected abstract int filter(int row, int col, int[][] channelPixels);

}
