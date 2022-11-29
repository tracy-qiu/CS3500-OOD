package controller;

import java.io.IOException;

import model.ImageModel;

/**
 * Interface that represents command function objects used to apply methods to the Image Model.
 * These are called in the controller and used on the model in order to edit/alter a given image
 * within the model.
 */
public interface ImageCommand {

  /**
   * method that applies a function object with functionality for changing an image, to the model.
   * @param model ImageProcessor model.
   * @throws IllegalArgumentException if the image is invalid and/or can't initialize the model.
   * @throws IOException for readPPM (if file isn't found) and writePPM (file isn't found).
   */
  void goCommand(ImageModel model) throws IllegalArgumentException, IOException;
}
