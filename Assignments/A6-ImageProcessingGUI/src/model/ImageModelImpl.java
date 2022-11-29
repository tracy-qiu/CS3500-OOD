package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents an implementation of the model for the ImageProcessor. The image model
 * stores a hashmap of images which can be added to and retrieved from. The model determines the
 * state of the images stored.
 */
public class ImageModelImpl implements ImageModel {
  private Map<String, ImageImpl> imageList;

  /**
   * Constructor for the model of the ImageProcessor program. Initializes a new empty hashmap that
   * stores String file names and images.
   */
  public ImageModelImpl() {
    this.imageList = new HashMap<String, ImageImpl>();
  }

  @Override
  public void addImage(String imageName, ImageImpl image) {
    if (image == null) {
      throw new IllegalArgumentException("Image null");
    }
    imageList.put(imageName, image);
  }

  @Override
  public ImageImpl getImageAt(String fileName) throws IllegalArgumentException {
    if (!this.imageList.containsKey(fileName)) {
      throw new IllegalArgumentException(String.format(
              "File name does not exist, please try again"));
    } else {
      return this.imageList.get(fileName);
    }
  }

  @Override
  public int[][] getHistogramAt(String fileName) throws IllegalArgumentException {
    if (!this.imageList.containsKey(fileName)) {
      throw new IllegalArgumentException(String.format(
              "File name does not exist, please try again"));
    } else {
      return this.imageList.get(fileName).makeHistogram();
    }
  }
}
