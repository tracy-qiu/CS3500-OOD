package controller.cmds;

import java.util.Scanner;

/**
 * Tests a specific instance of abstract commands.
 */
public class DarkenCmdTest extends AbstractCmdTest {

  /**
   * Creates an instance to abstract test.
   */
  public DarkenCmdTest() {
    super(new Scanner("koala blue-koala 32"), "koala"
            , "Command: darken koala blue-koala 32\n");
  }

  @Override
  protected ImageProcessorCmds createCmd(Scanner input) {
    return new DarkenCmd(input);
  }
}