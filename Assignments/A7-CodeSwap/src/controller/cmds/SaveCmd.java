package controller.cmds;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageProcessor;
import model.ImageUtil;
import model.PixelImage;

/**
 * The command that saves an image.
 */
public class SaveCmd extends AbstractCmd {
  /**
   * Constructs a savecmd.
   *
   * @param input the input into the command.
   */
  public SaveCmd(Scanner input) {
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
      PixelImage img = imgPro.getLoadedImg(destName);
      String imgFormat = this.getImageFormat(imgName);
      if (!imgName.substring(imgName.length() - 4, imgName.length()).equals(".ppm")) {
        File output = new File(imgName);
        ImageIO.write(ImageUtil.pixelToBuffered(img), imgFormat, output);

      } else {
        img.saveImg(imgName);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  private String getImageFormat(String fileName) {
    for (int i = fileName.length() - 1; i >= 0; i--) {
      if (fileName.charAt(i) == '.') {
        return fileName.substring(i + 1);
      }
    }
    throw new IllegalArgumentException("Filename doesnt have an extension");
  }
}
