package controller;

import java.awt.event.ActionListener;

/**
 * Interface that represents the controller that helps the model and the GUI communicate.
 */
public interface GUIController extends ActionListener {

  /**
   * Applies a given command to the model, resulting in a change in an image in the Image Processor.
   *
   * @param command command (function object) being applied the model.
   */
  void execute(ImageCommand command);
}
