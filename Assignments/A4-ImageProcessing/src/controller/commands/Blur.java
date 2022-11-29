package controller.commands;

import java.io.IOException;

import controller.ImageCommand;
import model.BlurImageProcessCommand;
import model.GreyscaleImageProcessCommand;
import model.IImageProcessCommand;
import model.ImageImpl;
import model.ImageModel;

public class Blur implements ImageCommand {
  String channel;
  String imageName;
  String destName;


  public Blur(String channel, String imageName, String destName){
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
    int[][] histogram = model.getHistogramAt(imageName);
    IImageProcessCommand iImageProcessCommand = new BlurImageProcessCommand();
    model.addImage(destName, iImageProcessCommand.run(newImage));
  }
}
