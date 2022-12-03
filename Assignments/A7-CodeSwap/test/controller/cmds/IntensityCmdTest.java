package controller.cmds;

import java.util.Scanner;


/**
 * Tests the intensity command.
 */
public class IntensityCmdTest extends AbstractCmdTest {

  /**
   * Consturcts an abstractCmdTest.
   */
  public IntensityCmdTest() {
    super(new Scanner("koala luma-koala"), "koala",
            "Command: greyscale koala luma-koala intensity\n");
  }

  /**
   * Create the specific command.
   *
   * @param input scanner
   * @return return a command to do stuff on
   */
  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new IntensityCmd(input);
  }
}
