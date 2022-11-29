package controller.commands;

import java.io.IOException;

import controller.ImageCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;
import model.SharpenImageProcessCommand;

/**
 * Class represents Sharpen function object that applies the process command of Sharpen to an
 * image in the model.
 */
public class Sharpen implements ImageCommand {
  private final String imageName;
  private final String destName;


  /**
   * constructor for Sharpen Command function object. Takes in image name
   * and new changed image name.
   *
   * @param imageName name of image.
   * @param destName  name of new changed image.
   */
  public Sharpen(String imageName, String destName) {
    this.imageName = imageName;
    this.destName = destName;
  }

  /**
   * method that applies Sharpen function object to an image in teh model.
   * Applies an iImageProcessCommand  (functionality) associated with Sharpen.
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
    IImageProcessCommand iImageProcessCommand = new SharpenImageProcessCommand();
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }
}
