package controller.cmds;

import org.junit.Test;

import java.util.Objects;
import java.util.Scanner;

import controller.ConfirmInputsModel;

import static org.junit.Assert.assertEquals;


/**
 * Abstracts tests all the commands.
 */
public abstract class AbstractCmdTest {

  Scanner input;

  String nextInput;
  String commandOutput;


  /**
   * Consturcts an abstractCmdTest.
   *
   * @param input         input
   * @param nextInput     nextval
   * @param commandOutput output of command
   */
  public AbstractCmdTest(Scanner input, String nextInput, String commandOutput) {
    this.nextInput = nextInput;
    Objects.requireNonNull(input);
    Objects.requireNonNull(commandOutput);
    this.input = input;
    this.commandOutput = commandOutput;
  }

  /**
   * Create the specific command.
   *
   * @param input scanner
   * @return return a command to do stuff on
   */
  protected abstract ImageProcessorCmds createCmd(Scanner input);

  @Test
  public void execute() {
    ImageProcessorCmds cmd = createCmd(input);
    StringBuilder log = new StringBuilder();
    cmd.execute(new ConfirmInputsModel(log));
    assertEquals(commandOutput, log.toString());
  }

  @Test
  public void getNextInput() {
    ImageProcessorCmds cmd = createCmd(input);
    assertEquals(nextInput, cmd.getNextInput());
  }
}