package controller.cmds;

import java.util.Scanner;


/**
 * Tests the blur command.
 */
public class BlurCmdTest extends AbstractCmdTest {

  /**
   * Consturcts an abstractCmdTest.
   */
  public BlurCmdTest() {
    super(new Scanner("koala luma-koala"), "koala",
            "Command: filter koala luma-koala blur\n");
  }

  /**
   * Create the specific command.
   *
   * @param input scanner
   * @return return a command to do stuff on
   */
  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new BlurCmd(input);
  }
}
