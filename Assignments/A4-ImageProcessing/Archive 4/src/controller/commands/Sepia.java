package controller.commands;

import java.io.IOException;

import controller.ImageCommand;
import model.GreyscaleImageProcessCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;
import model.SepiaImageProcessCommand;

public class Sepia implements ImageCommand {
  String imageName;
  String destName;

  public Sepia(String imageName, String destName){
    this.imageName = imageName;
    this.destName = destName;
  }

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
