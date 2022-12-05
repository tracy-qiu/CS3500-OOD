package model.filters;

import model.PixelImage;

/**
 * Class that represents the Image Mosaic functionality that can be applied to a given image.
 */
public interface IMosaic {

  /**
   * Method that applies the image mosaic functionality to a given image and return a new mosaic
   * image.
   * @return new image that has has mosaic functionality applied to it.
   */
  PixelImage createMosaic();
}
