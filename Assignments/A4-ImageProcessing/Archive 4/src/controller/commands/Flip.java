package controller.commands;

import controller.ImageCommand;
import model.FlipImageProcessCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;

/**
 * Class represents flip function object that applies the process command of Flip to an
 * image in the model.
 */
public class Flip implements ImageCommand {

  boolean horizontal;
  String imageName;
  String destName;

  /**
   * Constructor for the Flip Image Command Function object. Takes in the type of flip, the Image
   * Name and the new Image name.
   *
   * @param horizontal type of flip.
   * @param imageName  original image name.
   * @param destName   new image name.
   */
  public Flip(boolean horizontal, String imageName, String destName) {
    this.horizontal = horizontal;
    this.imageName = imageName;
    this.destName = destName;
  }

  /**
   * method that applies Flip function object to an image in teh model.
   * Applies an iImageProcessCommand  (functionality) associated with Flip.
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
    IImageProcessCommand iImageProcessCommand = new FlipImageProcessCommand(horizontal);
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }
}
