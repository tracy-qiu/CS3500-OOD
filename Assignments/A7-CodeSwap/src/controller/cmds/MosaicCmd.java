package controller.cmds;

import java.util.Scanner;

import model.ImageProcessor;

public class MosaicCmd extends AbstractCmd {

  public MosaicCmd(Scanner input) {
    super(input);
  }

  @Override
  protected void specificCommand(String imgName, String destName, ImageProcessor imgPro) {
    String numSeedsStr = this.getNextInput();

    int numSeeds = Integer.parseInt(numSeedsStr);
    imgPro.mosaicImage(imgName, destName, numSeeds);
  }
}
