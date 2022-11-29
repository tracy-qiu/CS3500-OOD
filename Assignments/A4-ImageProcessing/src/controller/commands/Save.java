package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageCommand;
import controller.ImageUtil;
import model.ImageImpl;
import model.ImageModel;

/**
 * Class represents function object of Save command that is used to convert an image in the
 * model to a PPM and then save it to your computer.
 */
public class Save implements ImageCommand {
  String filePath;
  String imageName;

  /**
   * constructor for Save Image Command. Takes in file path (location of image in computer)
   * and the image name.
   *
   * @param filePath  location of image.
   * @param imageName name of image.
   */
  public Save(String filePath, String imageName) {
    this.filePath = filePath;
    this.imageName = imageName;
  }


  /**
   * Method applies writePPM to the image in the model. Writes filePath tp file output stream.
   * Gets particular image from model an saves it to computer.
   *
   * @param model ImageProcessor model.
   * @throws IllegalArgumentException if the given imageName is not in the model or
   *                                  model is null.
   * @throws IOException              write method fails in writePPM method.
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

    switch (extension) {
      case "ppm":
        FileOutputStream outfile;
        outfile = new FileOutputStream(this.filePath);
        ImageImpl image = model.getImageAt(imageName);
        ImageUtil.writePPM(image, outfile);
        break;
      case "jpg":
        ImageImpl imageJPG = model.getImageAt(imageName);
        BufferedImage bufferedImage = ImageUtil.writeImageFile(imageJPG);
        File file = new File(this.filePath);
        ImageIO.write(bufferedImage, "jpg", file);
      case "png":
        ImageImpl imagePNG = model.getImageAt(imageName);
        BufferedImage bufferedImagePNG = ImageUtil.writeImageFile(imagePNG);
        File filePNG = new File(this.filePath);
        ImageIO.write(bufferedImagePNG, "png", filePNG);
      case "bmp":
        ImageImpl imageBMP = model.getImageAt(imageName);
        BufferedImage bufferedImageBMP = ImageUtil.writeImageFile(imageBMP);
        File fileBMP = new File(this.filePath);
        ImageIO.write(bufferedImageBMP, "bmp", fileBMP);
    }
  }

}
