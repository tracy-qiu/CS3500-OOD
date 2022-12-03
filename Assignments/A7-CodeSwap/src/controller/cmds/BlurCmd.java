package controller.cmds;

import java.util.Scanner;

import model.ImageProcessor;

/**
 * A command that calls the blur function in the model.
 */
public class BlurCmd extends AbstractCmd {
  /**
   * Constructs an Abstract Cmd.
   *
   * @param input input into the command.
   */
  public BlurCmd(Scanner input) {
    super(input);
  }

  /**
   * What to do with the image name and dest name.
   *
   * @param imgName  the name of the image in the processor
   * @param destName the name of the new image after the process
   * @param imgPro   the model to do the process on
   */
  @Override
  protected void specificCommand(String imgName, String destName, ImageProcessor imgPro) {
    imgPro.filterImage(imgName, destName, "blur");
  }
}
