package controller.cmds;

import java.util.Scanner;

import model.ImageProcessor;

public class MosaicCmd extends AbstractCmd {

  int numSeeds;

  public MosaicCmd(Scanner input, int numSeeds) {
    super(input);
    if (numSeeds <= 0){
      throw new IllegalArgumentException("Invalid number of seeds");
    }
    this.numSeeds = numSeeds;
  }

  @Override
  protected void specificCommand(String imgName, String destName, ImageProcessor imgPro) {
    imgPro.mosaicImage(imgName, destName, numSeeds);
  }
}
