package controller.commands;

import controller.ImageCommand;
import model.BrightenImageProcessCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;

/**
 * Class represents Brighten function object that applies the process command of Brighten to an
 * image in the model.
 */
public class Brighten implements ImageCommand {

  private final int brightnessChange;
  private final String imageName;
  private final String destName;

  /**
   * constructor for Brighten Command function object. Takes in brightness change, image name
   * and new changed image name.
   *
   * @param brightnessChange change in color.
   * @param imageName        name of image.
   * @param destName         name of new changed image.
   */
  public Brighten(int brightnessChange, String imageName, String destName) {
    this.brightnessChange = brightnessChange;
    this.imageName = imageName;
    this.destName = destName;
  }

  /**
   * method that applies Brighten function object to an image in teh model.
   * Applies an iImageProcessCommand  (functionality) associated with Brighten.
   *
   * @param model ImageProcessor model.
   * @throws IllegalArgumentException when model does not have image of imageName passed in or
   *                                  model is null.
   */
  @Override
  public void goCommand(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model null");
    }
    ImageImpl newImage = model.getImageAt(imageName);
    IImageProcessCommand iImageProcessCommand = new BrightenImageProcessCommand(brightnessChange);
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }
}
