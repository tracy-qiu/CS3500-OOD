package controller.commands;

import java.io.IOException;

import controller.ImageCommand;
import model.BlurImageProcessCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;

/**
 * Class represents Blur function object that applies the process command of Blur to an
 * image in the model.
 */
public class Blur implements ImageCommand {
  private final String imageName;
  private final String destName;


  /**
   * constructor for Blur Command function object. Takes in image name
   * and new changed image name.
   *
   * @param imageName name of image.
   * @param destName  name of new changed image.
   */
  public Blur(String imageName, String destName) {
    this.imageName = imageName;
    this.destName = destName;
  }

  /**
   * method that applies Blur function object to an image in teh model.
   * Applies an iImageProcessCommand  (functionality) associated with Blur.
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
    IImageProcessCommand iImageProcessCommand = new BlurImageProcessCommand();
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }
}
