package controller.cmds;

import java.util.Scanner;

import model.ImageProcessor;


/**
 * Abstracts the delegation of every command to the model.
 */
public abstract class AbstractCmd implements ImageProcessorCmds {

  private final Scanner input;

  /**
   * Constructs an Abstract Cmd.
   *
   * @param input input into the command.
   */
  public AbstractCmd(Scanner input) {
    if (input == null) {
      throw new IllegalArgumentException("Cant execute with a null scanner");
    }
    this.input = input;
  }


  /**
   * Execute this command.
   *
   * @param imgPro the processor on which to execute.
   */
  @Override
  public void execute(ImageProcessor imgPro) {
    String imgName = this.getNextInput();
    String destName = this.getNextInput();
    this.specificCommand(imgName, destName, imgPro);
  }

  /**
   * What to do with the image name and dest name.
   *
   * @param imgName  the name of the image in the processor
   * @param destName the name of the new image after the process
   * @param imgPro   the model to do the process on
   */
  protected abstract void specificCommand(String imgName, String destName, ImageProcessor imgPro);


  /**
   * Gets the next input in the scanner of this command.
   *
   * @return the next input as a string
   */
  public String getNextInput() {
    if (input.hasNext()) {
      return input.next();
    } else {
      throw new IllegalArgumentException("Input ran out: not enough input to execute command");
    }
  }
}
