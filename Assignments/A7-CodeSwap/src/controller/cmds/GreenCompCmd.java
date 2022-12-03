package controller.cmds;

import java.util.Scanner;

import model.ImageProcessor;

/**
 * The command that executes a green component.
 */
public class GreenCompCmd extends AbstractCmd {
  /**
   * Constructs a greencompcmd.
   *
   * @param input the input into the command
   */
  public GreenCompCmd(Scanner input) {
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
    imgPro.extractComponent(imgName, destName, "green");
  }
}
