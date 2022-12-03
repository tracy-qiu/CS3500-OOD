package controller.cmds;

import java.util.Scanner;

import model.ImageProcessor;

/**
 * The command that horizontally flips.
 */
public class HorizFlipCmd extends AbstractCmd {
  /**
   * Constructs a horizflipcmd.
   *
   * @param input the input into the command
   */
  public HorizFlipCmd(Scanner input) {
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
    imgPro.flipImage(imgName, destName, "horizontal");
  }
}
