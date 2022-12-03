package controller.cmds;

import model.ImageProcessor;


/**
 * Commands the users of the Image Processor can use.
 */
public interface ImageProcessorCmds {

  /**
   * Execute this command.
   *
   * @param imgPro the processor on which to execute.
   */
  void execute(ImageProcessor imgPro);

  /**
   * Gets the next input in the scanner of this command.
   *
   * @return the next input as a string.
   */
  String getNextInput();
}
