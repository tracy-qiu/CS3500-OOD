package controller.commands;

import java.io.IOException;

import controller.ImageCommand;
import controller.ImageUtil;
import model.ImageImpl;
import model.ImageModel;

/**
 * Class represents Load function object that applies readPPM to an image in the model.
 */
public class Load implements ImageCommand {
  String filePath;
  String fileName;

  /**
   * Constructor for the Load Image command. Takes in the filePath and file(image) name.
   *
   * @param filePath path of image in computer.
   * @param fileName name of file/image.
   */
  public Load(String filePath, String fileName) {
    this.filePath = filePath;
    this.fileName = fileName;
  }

  /**
   * Method applies the readPPM function to the file path and converts PPM to an image. This image
   * is then added to the model.
   *
   * @param model ImageProcessor model.
   * @throws IllegalArgumentException Negative width and height of Image or
   *                                  model is null.
   * @throws IOException              File is not found.
   */
  @Override
  public void goCommand(ImageModel model) throws IllegalArgumentException, IOException {
    if (model == null) {
      throw new IllegalArgumentException("Model null");
    }

    String extension = "";
    int i = filePath.lastIndexOf('.');
    if (i > 0) {
      extension = filePath.substring(i+1);
    }

    ImageImpl newImage = null;
    switch (extension) {
      case "ppm":
        newImage = ImageUtil.readPPM(filePath);
        break;
      case "jpg":
      case "png":
        newImage = ImageUtil.readImageFile(filePath);
      default:
        break;
    }
    model.addImage(fileName, newImage);
  }
}
