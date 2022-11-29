package controller.commands;

import java.io.IOException;

import controller.ImageCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;
import model.SepiaImageProcessCommand;

/**
 * Class represents Sepia function object that applies the process command of Sepia to an
 * image in the model.
 */
public class Sepia implements ImageCommand {
  private final String imageName;
  private final String destName;

  /**
   * constructor for Sepia Command function object. Takes in image name
   * and new changed image name.
   *
   * @param imageName name of image.
   * @param destName  name of new changed image.
   */
  public Sepia(String imageName, String destName) {
    this.imageName = imageName;
    this.destName = destName;
  }

  /**
   * method that applies Sepia function object to an image in teh model.
   * Applies an iImageProcessCommand  (functionality) associated with Sepia.
   *
   * @param model ImageProcessor model.
   * @throws IllegalArgumentException when model does not have image of imageName passed in or
   *                                  model is null.
   */
  @Override
  public void goCommand(ImageModel model) throws IllegalArgumentException, IOException {
    if (model == null) {
      throw new IllegalArgumentException("Model null");
    }
    ImageImpl newImage = model.getImageAt(imageName);
    IImageProcessCommand iImageProcessCommand = new SepiaImageProcessCommand();
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }
}
