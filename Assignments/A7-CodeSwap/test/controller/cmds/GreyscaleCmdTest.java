package controller.cmds;


import java.util.Scanner;

/**
 * Tests a specific instance of abstract commands.
 */
public class GreyscaleCmdTest extends AbstractCmdTest {


  /**
   * Creates an instance to abstract test.
   */
  public GreyscaleCmdTest() {
    super(new Scanner("koala blue-koala greyscale"), "koala"
            , "Command: filter koala blue-koala greyscale\n");
  }

  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new GreyscaleCmd(input);
  }
}