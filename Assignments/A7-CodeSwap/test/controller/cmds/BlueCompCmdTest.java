package controller.cmds;

import java.util.Scanner;


/**
 * Tests a specific instance of abstract commands.
 */
public class BlueCompCmdTest extends AbstractCmdTest {

  /**
   * Creates an instance to abstract test.
   */
  public BlueCompCmdTest() {
    super(new Scanner("koala blue-koala blue"), "koala"
            , "Command: extract-component koala blue-koala blue\n");
  }

  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new BlueCompCmd(input);
  }
}