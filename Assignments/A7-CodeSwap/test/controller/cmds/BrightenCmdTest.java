package controller.cmds;

import java.util.Scanner;

/**
 * Tests a specific instance of abstract commands.
 */
public class BrightenCmdTest extends AbstractCmdTest {

  /**
   * Creates an instance to abstract test.
   */
  public BrightenCmdTest() {
    super(new Scanner("koala blue-koala 32"), "koala"
            , "Command: brighten koala blue-koala 32\n");
  }

  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new BrightenCmd(input);
  }
}