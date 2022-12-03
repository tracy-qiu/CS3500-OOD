package controller.cmds;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import model.ImageProcessor;

/**
 * The command that loads an image.
 */
public class LoadCmd extends AbstractCmd {
  /**
   * Constructs a loadcmd.
   *
   * @param input the input into the command
   */
  public LoadCmd(Scanner input) {
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
    try {
      File f = new File(imgName);
      imgPro.loadImage(f, destName);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}