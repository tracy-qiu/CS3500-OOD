package controller.commands;

import java.io.IOException;

import controller.ImageCommand;
import model.BlurImageProcessCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;
import model.SharpenImageProcessCommand;

public class Sharpen implements ImageCommand {

  String channel;
  String imageName;
  String destName;


  public Sharpen(String channel, String imageName, String destName){
    this.channel = channel;
    this.imageName = imageName;
    this.destName = destName;
  }

  @Override
  public void goCommand(ImageModel model) throws IllegalArgumentException, IOException {
    if (model == null) {
      throw new IllegalArgumentException("Model null");
    }
    ImageImpl newImage = model.getImageAt(imageName);
    IImageProcessCommand iImageProcessCommand = new SharpenImageProcessCommand(channel);
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }
}
