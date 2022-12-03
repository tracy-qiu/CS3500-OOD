package controller.cmds;

import java.util.Scanner;


/**
 * Tests a specific instance of abstract commands.
 */
public class LoadCmdTest extends AbstractCmdTest {


  /**
   * Creates an instance to abstract test.
   */
  public LoadCmdTest() {
    super(new Scanner("koala blue-koala"), "koala"
            , "Command: load koala blue-koala\n");
  }

  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new LoadCmd(input);
  }
}