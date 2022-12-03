package controller.cmds;

import java.util.Scanner;


/**
 * Tests the sepia command.
 */
public class SepiaCmdTest extends AbstractCmdTest {

  /**
   * Consturcts an abstractCmdTest.
   */
  public SepiaCmdTest() {
    super(new Scanner("koala luma-koala"), "koala",
            "Command: filter koala luma-koala sepia\n");
  }

  /**
   * Create the specific command.
   *
   * @param input scanner
   * @return return a command to do stuff on
   */
  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new SepiaCmd(input);
  }
}
