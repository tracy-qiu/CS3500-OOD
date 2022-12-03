package controller.cmds;

import java.util.Scanner;


/**
 * Tests the luma command.
 */
public class LumaCmdTest extends AbstractCmdTest {

  /**
   * Constructs an abstractCmdTest.
   */
  public LumaCmdTest() {
    super(new Scanner("koala luma-koala"), "koala",
            "Command: greyscale koala luma-koala luma\n");
  }

  /**
   * Create the specific command.
   *
   * @param input scanner
   * @return return a command to do stuff on
   */
  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new LumaCmd(input);
  }
}
