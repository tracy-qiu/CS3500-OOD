package controller.cmds;

import java.util.Scanner;

import model.ImageProcessor;

/**
 * The command that executes a brightened image.
 */
public class BrightenCmd extends AbstractCmd {

  /**
   * Constructs a brightencmd.
   *
   * @param input the input into the command
   */
  public BrightenCmd(Scanner input) {
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
    String factorStr = this.getNextInput();

    int factor = Integer.parseInt(factorStr);
    imgPro.adjustBrightness(imgName, factor, destName);
  }
}
