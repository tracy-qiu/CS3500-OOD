package controller.cmds;

import java.util.Scanner;


/**
 * Tests the value command.
 */
public class ValueCmdTest extends AbstractCmdTest {

  /**
   * Constructs an abstractCmdTest.
   */
  public ValueCmdTest() {
    super(new Scanner("koala luma-koala"), "koala",
            "Command: greyscale koala luma-koala value\n");
  }

  /**
   * Create the specific command.
   *
   * @param input scanner
   * @return return a command to do stuff on
   */
  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new ValueCmd(input);
  }
}
