package model;

/**
 * Interface that represents the model of image processor program used to store images
 * in a hashmap. Images can be added to the hashmap and references by fetching with the image name.
 */
public interface ImageModel {
  /**
   * Method that allows images to be added to the hashmap of images.
   * @param imageName String image name used to label the image in hashmap
   * @param imageImpl image to be added to hashmap
   */
  void addImage(String imageName, ImageImpl imageImpl);

  /**
   * Method that searched the hashmap looking for the image with the given file name.
   * @param fileName String key value used to search for image in hashmap
   * @return Image at the with the key value of the file name
   * @throws IllegalArgumentException if the file is not found
   */
  ImageImpl getImageAt(String fileName) throws IllegalArgumentException;
}
