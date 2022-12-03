package controller.cmds;

import java.util.Scanner;


/**
 * Tests a specific instance of abstract commands.
 */
public class HorizFlipCmdTest extends AbstractCmdTest {


  /**
   * Creates an instance to abstract test.
   */
  public HorizFlipCmdTest() {
    super(new Scanner("koala blue-koala"), "koala"
            , "Command: flip-horizontal koala blue-koala\n");
  }

  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new HorizFlipCmd(input);
  }
}