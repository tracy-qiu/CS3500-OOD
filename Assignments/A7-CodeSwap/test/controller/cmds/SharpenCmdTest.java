package controller.cmds;

import java.util.Scanner;


/**
 * Tests the luma command.
 */
public class SharpenCmdTest extends AbstractCmdTest {

  /**
   * Consturcts an abstractCmdTest.
   */
  public SharpenCmdTest() {
    super(new Scanner("koala luma-koala"), "koala",
            "Command: filter koala luma-koala sharpen\n");
  }

  /**
   * Create the specific command.
   *
   * @param input scanner
   * @return return a command to do stuff on
   */
  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new SharpenCmd(input);
  }
}
