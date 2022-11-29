package controller.commands;

import controller.ImageCommand;
import model.GreyscaleImageProcessCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;

/**
 * Class represents Greyscale function object that applies the process command of Greyscale to an
 * image in the model.
 */
public class Greyscale implements ImageCommand {
  private final String componentType;
  private final String imageName;
  private final String destName;

  /**
   * Constructor for the Greyscale image command. Takes in the type of Grayscale function
   * as a string. The name of the original image. And the name of the new image.
   *
   * @param componentType type of Greyscale applied to image.
   * @param imageName     original image name.
   * @param destName      new image name.
   */
  public Greyscale(String componentType, String imageName, String destName) {
    this.componentType = componentType;
    this.imageName = imageName;
    this.destName = destName;
  }

  /**
   * method that applies Greyscale function object to an image in teh model.
   * Applies an iImageProcessCommand  (functionality) associated with Greyscale.
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
    IImageProcessCommand iImageProcessCommand = new GreyscaleImageProcessCommand(componentType);
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }

}
