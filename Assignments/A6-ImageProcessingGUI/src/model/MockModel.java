package model;

import java.io.IOException;

/**
 * represents MockModel used for testing purposes to determine that the model and the controller
 * are communicating correctly. Takes in an appendable that logs what the controller is
 * sending the model.
 */
public class MockModel implements ImageModel {

  private final Appendable log;

  /**
   * represents the constructor that takes in the appendable that logs what controller sends
   * to model.
   *
   * @param log appendable that logs activity.
   */
  public MockModel(Appendable log) {
    if (log == null) {
      throw new IllegalArgumentException("Log is null");
    }
    this.log = log;
  }

  /**
   * methods that appends messages to the log when you call addImage. Appends image name,
   * width and height of the image.
   *
   * @param imageName represents image name.
   * @param imageImpl represents image implementation.
   * @throws IllegalStateException when appendable is null.
   */
  @Override
  public void addImage(String imageName, ImageImpl imageImpl) {
    try {
      log.append(imageName).append(" ").append(String.valueOf(imageImpl.getWidth())).append(" ")
              .append(String.valueOf(imageImpl.getHeight())).append(" ");
    } catch (IOException e) {
      throw new IllegalStateException("Illegal State Exception");
    }
  }

  /**
   * MockModel getImageAt method. Not used to test the mock model. Stub method.
   *
   * @param fileName name of file.
   * @return Image.
   */
  @Override
  public ImageImpl getImageAt(String fileName) {
    return null;
  }

  @Override
  public int[][] getHistogramAt(String fileName) {
    return null;
  }
}
